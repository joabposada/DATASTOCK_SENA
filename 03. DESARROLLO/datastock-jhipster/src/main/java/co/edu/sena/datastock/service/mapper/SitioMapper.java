package co.edu.sena.datastock.service.mapper;

import co.edu.sena.datastock.domain.Sitio;
import co.edu.sena.datastock.service.dto.SitioDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Sitio} and its DTO {@link SitioDTO}.
 */
@Mapper(componentModel = "spring")
public interface SitioMapper extends EntityMapper<SitioDTO, Sitio> {}
