package co.sena.edu.datastock.seguridad;

import co.sena.edu.datastock.exception.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }

    public Rol buscarPorId(Integer id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe un rol con el ID: " + id));
    }

    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol actualizar(Integer id, Rol datosNuevos) {
        Rol existente = buscarPorId(id);
        existente.setNombreRol(datosNuevos.getNombreRol());
        return rolRepository.save(existente);
    }

    public void eliminar(Integer id) {
        Rol existente = buscarPorId(id);
        rolRepository.delete(existente);
    }
}
