package co.sena.edu.datastock.seguridad;

public record AsignarRolRequestDTO(
        Integer idUsuario,
        Integer idRol,
        String estado
) {}