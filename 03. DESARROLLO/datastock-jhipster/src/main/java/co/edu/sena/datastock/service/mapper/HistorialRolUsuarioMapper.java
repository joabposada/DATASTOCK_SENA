package co.edu.sena.datastock.service.mapper;

import co.edu.sena.datastock.domain.HistorialRolUsuario;
import co.edu.sena.datastock.domain.Rol;
import co.edu.sena.datastock.domain.Usuario;
import co.edu.sena.datastock.service.dto.HistorialRolUsuarioDTO;
import co.edu.sena.datastock.service.dto.RolDTO;
import co.edu.sena.datastock.service.dto.UsuarioDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HistorialRolUsuario} and its DTO {@link HistorialRolUsuarioDTO}.
 */
@Mapper(componentModel = "spring")
public interface HistorialRolUsuarioMapper extends EntityMapper<HistorialRolUsuarioDTO, HistorialRolUsuario> {
    @Mapping(target = "usuario", source = "usuario", qualifiedByName = "usuarioNombre")
    @Mapping(target = "rol", source = "rol", qualifiedByName = "rolNombreRol")
    HistorialRolUsuarioDTO toDto(HistorialRolUsuario s);

    @Named("usuarioNombre")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    UsuarioDTO toDtoUsuarioNombre(Usuario usuario);

    @Named("rolNombreRol")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombreRol", source = "nombreRol")
    RolDTO toDtoRolNombreRol(Rol rol);
}
