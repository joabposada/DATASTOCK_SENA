package co.sena.edu.datastock.catalogo;

import co.sena.edu.datastock.exception.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> listarTodas() {
        return marcaRepository.findAll();
    }

    public Marca buscarPorId(Integer id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe una marca con el ID: " + id));
    }

    public Marca guardar(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca actualizar(Integer id, Marca datosNuevos) {
        Marca existente = buscarPorId(id);
        existente.setNombreMarca(datosNuevos.getNombreMarca());
        return marcaRepository.save(existente);
    }

    public void eliminar(Integer id) {
        Marca existente = buscarPorId(id);
        marcaRepository.delete(existente);
    }
}