package co.edu.sena.datastock.service.mapper;

import co.edu.sena.datastock.domain.Usuario;
import co.edu.sena.datastock.service.dto.UsuarioDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Usuario} and its DTO {@link UsuarioDTO}.
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {}
