package co.sena.edu.datastock.inventario;

import co.sena.edu.datastock.catalogo.Producto;
import co.sena.edu.datastock.catalogo.ProductoRepository;
import co.sena.edu.datastock.exception.RecursoNoEncontradoException;
import co.sena.edu.datastock.infraestructura.Sitio;
import co.sena.edu.datastock.infraestructura.SitioRepository;
import co.sena.edu.datastock.seguridad.Usuario;
import co.sena.edu.datastock.seguridad.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoteEntradaService {

    private final LoteEntradaRepository loteRepository;
    private final InventarioActualRepository inventarioRepository;
    private final HistorialMovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;
    private final SitioRepository sitioRepository;
    private final UsuarioRepository usuarioRepository;

    public LoteEntradaService(LoteEntradaRepository loteRepository,
                              InventarioActualRepository inventarioRepository,
                              HistorialMovimientoRepository movimientoRepository,
                              ProductoRepository productoRepository,
                              SitioRepository sitioRepository,
                              UsuarioRepository usuarioRepository) {
        this.loteRepository = loteRepository;
        this.inventarioRepository = inventarioRepository;
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
        this.sitioRepository = sitioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<LoteEntrada> listarTodos() {
        return loteRepository.findAll();
    }

    public LoteEntrada buscarPorId(Integer id) {
        return loteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe un lote de entrada con el ID: " + id));
    }

    @Transactional
    public LoteEntrada registrarLote(LoteEntradaRequestDTO dto) {
        if (dto.idProducto() == null || dto.idSitio() == null || dto.idUsuario() == null) {
            throw new IllegalArgumentException("idProducto, idSitio e idUsuario son obligatorios.");
        }
        if (dto.cantidadIngresada() == null || dto.cantidadIngresada() <= 0) {
            throw new IllegalArgumentException("La cantidad ingresada debe ser mayor a 0.");
        }
        if (dto.fechaVencimiento() == null || dto.facturaProveedor() == null || dto.facturaProveedor().isBlank()) {
            throw new IllegalArgumentException("fechaVencimiento y facturaProveedor son obligatorios.");
        }

        Producto producto = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el producto con ID: " + dto.idProducto()));

        Sitio sitio = sitioRepository.findById(dto.idSitio())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el sitio con ID: " + dto.idSitio()));

        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el usuario con ID: " + dto.idUsuario()));

        // 1. Guardar el lote de entrada
        LoteEntrada lote = new LoteEntrada();
        lote.setProducto(producto);
        lote.setSitio(sitio);
        lote.setFechaIngreso(LocalDate.now());
        lote.setFechaVencimiento(dto.fechaVencimiento());
        lote.setCantidadIngresada(dto.cantidadIngresada());
        lote.setFacturaProveedor(dto.facturaProveedor());
        LoteEntrada loteGuardado = loteRepository.save(lote);

        // 2. Sumar las unidades al inventario actual del sitio
        InventarioActualId invId = new InventarioActualId(producto.getIdProducto(), sitio.getIdSitio());
        InventarioActual inventario = inventarioRepository.findById(invId).orElseGet(() -> {
            InventarioActual nuevo = new InventarioActual();
            nuevo.setId(invId);
            nuevo.setProducto(producto);
            nuevo.setSitio(sitio);
            nuevo.setStockDisponible(0);
            return nuevo;
        });
        inventario.setStockDisponible(inventario.getStockDisponible() + dto.cantidadIngresada());
        inventarioRepository.save(inventario);

        // 3. Registrar trazabilidad en historial_movimiento
        HistorialMovimiento movimiento = new HistorialMovimiento();
        movimiento.setProducto(producto);
        movimiento.setSitio(sitio);
        movimiento.setUsuario(usuario);
        movimiento.setTipoMovimiento("ENTRADA");
        movimiento.setCantidad(dto.cantidadIngresada());
        movimiento.setFechaHora(LocalDateTime.now());
        movimientoRepository.save(movimiento);

        return loteGuardado;
    }
}
