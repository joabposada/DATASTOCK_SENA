package co.edu.sena.datastock.service.mapper;

import co.edu.sena.datastock.domain.LoteEntrada;
import co.edu.sena.datastock.domain.Producto;
import co.edu.sena.datastock.domain.Sitio;
import co.edu.sena.datastock.service.dto.LoteEntradaDTO;
import co.edu.sena.datastock.service.dto.ProductoDTO;
import co.edu.sena.datastock.service.dto.SitioDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LoteEntrada} and its DTO {@link LoteEntradaDTO}.
 */
@Mapper(componentModel = "spring")
public interface LoteEntradaMapper extends EntityMapper<LoteEntradaDTO, LoteEntrada> {
    @Mapping(target = "producto", source = "producto", qualifiedByName = "productoNombreProducto")
    @Mapping(target = "sitio", source = "sitio", qualifiedByName = "sitioNombreSitio")
    LoteEntradaDTO toDto(LoteEntrada s);

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
