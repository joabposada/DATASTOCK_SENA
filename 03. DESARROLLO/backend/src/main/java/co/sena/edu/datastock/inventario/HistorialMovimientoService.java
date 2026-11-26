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

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistorialMovimientoService {

    private final HistorialMovimientoRepository movimientoRepository;
    private final InventarioActualRepository inventarioRepository;
    private final ProductoRepository productoRepository;
    private final SitioRepository sitioRepository;
    private final UsuarioRepository usuarioRepository;

    public HistorialMovimientoService(HistorialMovimientoRepository movimientoRepository,
                                      InventarioActualRepository inventarioRepository,
                                      ProductoRepository productoRepository,
                                      SitioRepository sitioRepository,
                                      UsuarioRepository usuarioRepository) {
        this.movimientoRepository = movimientoRepository;
        this.inventarioRepository = inventarioRepository;
        this.productoRepository = productoRepository;
        this.sitioRepository = sitioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<HistorialMovimiento> listarTodos() {
        return movimientoRepository.findAll();
    }

    @Transactional
    public HistorialMovimiento registrarMovimiento(MovimientoRequestDTO dto) {
        if (dto.idProducto() == null || dto.idSitio() == null || dto.idUsuario() == null) {
            throw new IllegalArgumentException("idProducto, idSitio e idUsuario son obligatorios.");
        }
        if (dto.tipoMovimiento() == null || dto.tipoMovimiento().isBlank()) {
            throw new IllegalArgumentException("El tipoMovimiento es obligatorio (SALIDA o TRASLADO).");
        }
        if (dto.cantidad() == null || dto.cantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }

        String tipo = dto.tipoMovimiento().trim().toUpperCase();

        Producto producto = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el producto con ID: " + dto.idProducto()));

        Sitio sitioOrigen = sitioRepository.findById(dto.idSitio())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el sitio origen con ID: " + dto.idSitio()));

        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el usuario con ID: " + dto.idUsuario()));

        InventarioActualId idOrigen = new InventarioActualId(producto.getIdProducto(), sitioOrigen.getIdSitio());
        InventarioActual invOrigen = inventarioRepository.findById(idOrigen)
                .orElseThrow(() -> new IllegalArgumentException("No hay inventario registrado para este producto en el sitio origen."));

        if (invOrigen.getStockDisponible() < dto.cantidad()) {
            throw new IllegalArgumentException("Stock insuficiente. Disponible: " + invOrigen.getStockDisponible() + ", solicitado: " + dto.cantidad());
        }

        if (tipo.equals("SALIDA")) {
            invOrigen.setStockDisponible(invOrigen.getStockDisponible() - dto.cantidad());
            inventarioRepository.save(invOrigen);
        } else if (tipo.equals("TRASLADO")) {
            if (dto.idSitioDestino() == null || dto.idSitioDestino().equals(dto.idSitio())) {
                throw new IllegalArgumentException("Para un TRASLADO debes indicar un idSitioDestino diferente al origen.");
            }
            Sitio sitioDestino = sitioRepository.findById(dto.idSitioDestino())
                    .orElseThrow(() -> new RecursoNoEncontradoException("No existe el sitio destino con ID: " + dto.idSitioDestino()));

            invOrigen.setStockDisponible(invOrigen.getStockDisponible() - dto.cantidad());
            inventarioRepository.save(invOrigen);

            InventarioActualId idDestino = new InventarioActualId(producto.getIdProducto(), sitioDestino.getIdSitio());
            InventarioActual invDestino = inventarioRepository.findById(idDestino).orElseGet(() -> {
                InventarioActual nuevo = new InventarioActual();
                nuevo.setId(idDestino);
                nuevo.setProducto(producto);
                nuevo.setSitio(sitioDestino);
                nuevo.setStockDisponible(0);
                return nuevo;
            });
            invDestino.setStockDisponible(invDestino.getStockDisponible() + dto.cantidad());
            inventarioRepository.save(invDestino);
        } else {
            throw new IllegalArgumentException("Tipo de movimiento no válido: " + tipo + ". Usa SALIDA o TRASLADO.");
        }

        HistorialMovimiento movimiento = new HistorialMovimiento();
        movimiento.setProducto(producto);
        movimiento.setSitio(sitioOrigen);
        movimiento.setIdSitioDestino(tipo.equals("TRASLADO") ? dto.idSitioDestino() : null);
        movimiento.setUsuario(usuario);
        movimiento.setTipoMovimiento(tipo);
        movimiento.setCantidad(dto.cantidad());
        movimiento.setFechaHora(LocalDateTime.now());

        return movimientoRepository.save(movimiento);
    }
}
