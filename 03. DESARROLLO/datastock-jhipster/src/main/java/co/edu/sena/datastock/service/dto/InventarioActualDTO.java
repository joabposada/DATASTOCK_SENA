package co.edu.sena.datastock.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.datastock.domain.InventarioActual} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InventarioActualDTO implements Serializable {

    private Long id;

    @NotNull
    @Min(value = 0)
    private Integer stockDisponible;

    @NotNull
    private ProductoDTO producto;

    @NotNull
    private SitioDTO sitio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public SitioDTO getSitio() {
        return sitio;
    }

    public void setSitio(SitioDTO sitio) {
        this.sitio = sitio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InventarioActualDTO)) {
            return false;
        }

        InventarioActualDTO inventarioActualDTO = (InventarioActualDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, inventarioActualDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InventarioActualDTO{" +
            "id=" + getId() +
            ", stockDisponible=" + getStockDisponible() +
            ", producto=" + getProducto() +
            ", sitio=" + getSitio() +
            "}";
    }
}
