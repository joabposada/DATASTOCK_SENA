package co.edu.sena.datastock.service.mapper;

import co.edu.sena.datastock.domain.Categoria;
import co.edu.sena.datastock.domain.Marca;
import co.edu.sena.datastock.domain.Producto;
import co.edu.sena.datastock.service.dto.CategoriaDTO;
import co.edu.sena.datastock.service.dto.MarcaDTO;
import co.edu.sena.datastock.service.dto.ProductoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Producto} and its DTO {@link ProductoDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductoMapper extends EntityMapper<ProductoDTO, Producto> {
    @Mapping(target = "categoria", source = "categoria", qualifiedByName = "categoriaNombreCategoria")
    @Mapping(target = "marca", source = "marca", qualifiedByName = "marcaNombreMarca")
    ProductoDTO toDto(Producto s);

    @Named("categoriaNombreCategoria")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombreCategoria", source = "nombreCategoria")
    CategoriaDTO toDtoCategoriaNombreCategoria(Categoria categoria);

    @Named("marcaNombreMarca")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombreMarca", source = "nombreMarca")
    MarcaDTO toDtoMarcaNombreMarca(Marca marca);
}
