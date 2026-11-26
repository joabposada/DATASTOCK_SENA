package co.edu.sena.datastock.service.mapper;

import co.edu.sena.datastock.domain.InventarioActual;
import co.edu.sena.datastock.domain.Producto;
import co.edu.sena.datastock.domain.Sitio;
import co.edu.sena.datastock.service.dto.InventarioActualDTO;
import co.edu.sena.datastock.service.dto.ProductoDTO;
import co.edu.sena.datastock.service.dto.SitioDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InventarioActual} and its DTO {@link InventarioActualDTO}.
 */
@Mapper(componentModel = "spring")
public interface InventarioActualMapper extends EntityMapper<InventarioActualDTO, InventarioActual> {
    @Mapping(target = "producto", source = "producto", qualifiedByName = "productoNombreProducto")
    @Mapping(target = "sitio", source = "sitio", qualifiedByName = "sitioNombreSitio")
    InventarioActualDTO toDto(InventarioActual s);

    @Named("productoNombreProducto")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombreProducto", source = "nombreProducto")
    ProductoDTO toDtoProductoNombreProducto(Producto producto);

    @Named("sitioNombreSitio")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombreSitio", source = "nombreSitio")
    SitioDTO toDtoSitioNombreSitio(Sitio sitio);
}
