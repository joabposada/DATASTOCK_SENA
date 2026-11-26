package co.edu.sena.datastock.service.mapper;

import co.edu.sena.datastock.domain.Categoria;
import co.edu.sena.datastock.service.dto.CategoriaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Categoria} and its DTO {@link CategoriaDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoriaMapper extends EntityMapper<CategoriaDTO, Categoria> {}
