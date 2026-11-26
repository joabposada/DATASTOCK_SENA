package co.edu.sena.datastock.service.mapper;

import co.edu.sena.datastock.domain.Rol;
import co.edu.sena.datastock.service.dto.RolDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Rol} and its DTO {@link RolDTO}.
 */
@Mapper(componentModel = "spring")
public interface RolMapper extends EntityMapper<RolDTO, Rol> {}
