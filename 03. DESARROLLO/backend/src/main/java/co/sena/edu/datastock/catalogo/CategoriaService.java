package co.sena.edu.datastock.catalogo;

import co.sena.edu.datastock.exception.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe una categoría con el ID: " + id));
    }

    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizar(Integer id, Categoria datosNuevos) {
        Categoria existente = buscarPorId(id);
        existente.setNombreCategoria(datosNuevos.getNombreCategoria());
        return categoriaRepository.save(existente);
    }

    public void eliminar(Integer id) {
        Categoria existente = buscarPorId(id);
        categoriaRepository.delete(existente);
    }
}