package co.sena.edu.datastock.seguridad;

import co.sena.edu.datastock.exception.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class HistorialRolUsuarioService {

    private final HistorialRolUsuarioRepository historialRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public HistorialRolUsuarioService(HistorialRolUsuarioRepository historialRepository,
                                      UsuarioRepository usuarioRepository,
                                      RolRepository rolRepository) {
        this.historialRepository = historialRepository;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    public List<HistorialRolUsuario> listarTodos() {
        return historialRepository.findAll();
    }

    public HistorialRolUsuario asignarRol(AsignarRolRequestDTO dto) {
        if (dto.idUsuario() == null || dto.idRol() == null) {
            throw new IllegalArgumentException("idUsuario e idRol son obligatorios.");
        }

        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el usuario con ID: " + dto.idUsuario()));

        Rol rol = rolRepository.findById(dto.idRol())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el rol con ID: " + dto.idRol()));

        HistorialRolUsuarioId llaveCompuesta = new HistorialRolUsuarioId(usuario.getIdUsuario(), rol.getIdRol());

        HistorialRolUsuario historial = new HistorialRolUsuario();
        historial.setId(llaveCompuesta);
        historial.setUsuario(usuario);
        historial.setRol(rol);
        historial.setFechaAsignacion(LocalDate.now());
        historial.setEstado((dto.estado() != null && !dto.estado().isBlank()) ? dto.estado() : "ACTIVO");

        return historialRepository.save(historial);
    }
}
