// interfaz hecha junto con la API que viene siendo la logica de negocio y validaciones
package co.sena.edu.datastock.infraestructura;

import co.sena.edu.datastock.exception.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SitioService {

    private final SitioRepository sitioRepository;

    // Inyección por constructor (mejor práctica en Spring) (IA)
    public SitioService(SitioRepository sitioRepository) {
        this.sitioRepository = sitioRepository;
    }

    public List<Sitio> listarTodos() {
        return sitioRepository.findAll();
    }

    public Sitio buscarPorId(Integer id) {
        return sitioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe un sitio con el ID: " + id));
    }

    public Sitio guardar(Sitio sitio) {
        return sitioRepository.save(sitio);
    }

    public SitioRepository getSitioRepository() {
        return sitioRepository;
    }

    public Sitio actualizar(Integer id, Sitio datosNuevos) {
        Sitio sitioExistente = buscarPorId(id);
        sitioExistente.setNombreSitio(datosNuevos.getNombreSitio());
        sitioExistente.setTipoSitio(datosNuevos.getTipoSitio());
        sitioExistente.setDireccion(datosNuevos.getDireccion());
        return sitioRepository.save(sitioExistente);
    }

    public void eliminar(Integer id) {
        Sitio sitioExistente = buscarPorId(id);
        sitioRepository.delete(sitioExistente);
    }
}
