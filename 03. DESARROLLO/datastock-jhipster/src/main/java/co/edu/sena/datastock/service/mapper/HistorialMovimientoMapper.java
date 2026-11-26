package co.edu.sena.datastock.service.mapper;

import co.edu.sena.datastock.domain.HistorialMovimiento;
import co.edu.sena.datastock.domain.Producto;
import co.edu.sena.datastock.domain.Sitio;
import co.edu.sena.datastock.domain.Usuario;
import co.edu.sena.datastock.service.dto.HistorialMovimientoDTO;
import co.edu.sena.datastock.service.dto.ProductoDTO;
import co.edu.sena.datastock.service.dto.SitioDTO;
import co.edu.sena.datastock.service.dto.UsuarioDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HistorialMovimiento} and its DTO {@link HistorialMovimientoDTO}.
 */
@Mapper(componentModel = "spring")
public interface HistorialMovimientoMapper extends EntityMapper<HistorialMovimientoDTO, HistorialMovimiento> {
    @Mapping(target = "producto", source = "producto", qualifiedByName = "productoNombreProducto")
    @Mapping(target = "sitio", source = "sitio", qualifiedByName = "sitioNombreSitio")
    @Mapping(target = "usuario", source = "usuario", qualifiedByName = "usuarioNombre")
    HistorialMovimientoDTO toDto(HistorialMovimiento s);

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

    @Named("usuarioNombre")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    UsuarioDTO toDtoUsuarioNombre(Usuario usuario);
}
