package co.sena.edu.datastock.catalogo;

import co.sena.edu.datastock.exception.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;

    public ProductoService(ProductoRepository productoRepository,
                           CategoriaRepository categoriaRepository,
                           MarcaRepository marcaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.marcaRepository = marcaRepository;
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe un producto con el ID: " + id));
    }

    public Producto guardar(ProductoRequestDTO dto) {
        if (dto.idCategoria() == null || dto.idMarca() == null) {
            throw new IllegalArgumentException("idCategoria e idMarca son obligatorios.");
        }

        Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la categoría con ID: " + dto.idCategoria()));

        Marca marca = marcaRepository.findById(dto.idMarca())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la marca con ID: " + dto.idMarca()));

        Producto producto = new Producto();
        producto.setNombreProducto(dto.nombreProducto());
        producto.setPresentacion(dto.presentacion());
        producto.setCategoria(categoria);
        producto.setMarca(marca);

        return productoRepository.save(producto);
    }

    public Producto actualizar(Integer id, ProductoRequestDTO dto) {
        Producto existente = buscarPorId(id);

        if (dto.idCategoria() == null || dto.idMarca() == null) {
            throw new IllegalArgumentException("idCategoria e idMarca son obligatorios.");
        }

        Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la categoría con ID: " + dto.idCategoria()));

        Marca marca = marcaRepository.findById(dto.idMarca())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la marca con ID: " + dto.idMarca()));

        existente.setNombreProducto(dto.nombreProducto());
        existente.setPresentacion(dto.presentacion());
        existente.setCategoria(categoria);
        existente.setMarca(marca);

        return productoRepository.save(existente);
    }

    public void eliminar(Integer id) {
        Producto existente = buscarPorId(id);
        productoRepository.delete(existente);
    }
}
