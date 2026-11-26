package co.edu.sena.datastock.service.mapper;

import co.edu.sena.datastock.domain.Marca;
import co.edu.sena.datastock.service.dto.MarcaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Marca} and its DTO {@link MarcaDTO}.
 */
@Mapper(componentModel = "spring")
public interface MarcaMapper extends EntityMapper<MarcaDTO, Marca> {}
