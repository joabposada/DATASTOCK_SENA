package co.edu.sena.datastock.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.datastock.domain.LoteEntrada} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoteEntradaDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate fechaIngreso;

    @NotNull
    private LocalDate fechaVencimiento;

    @NotNull
    @Min(value = 1)
    private Integer cantidadIngresada;

    @NotNull
    @Size(max = 50)
    private String facturaProveedor;

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

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getCantidadIngresada() {
        return cantidadIngresada;
    }

    public void setCantidadIngresada(Integer cantidadIngresada) {
        this.cantidadIngresada = cantidadIngresada;
    }

    public String getFacturaProveedor() {
        return facturaProveedor;
    }

    public void setFacturaProveedor(String facturaProveedor) {
        this.facturaProveedor = facturaProveedor;
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
        if (!(o instanceof LoteEntradaDTO)) {
            return false;
        }

        LoteEntradaDTO loteEntradaDTO = (LoteEntradaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, loteEntradaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoteEntradaDTO{" +
            "id=" + getId() +
            ", fechaIngreso='" + getFechaIngreso() + "'" +
            ", fechaVencimiento='" + getFechaVencimiento() + "'" +
            ", cantidadIngresada=" + getCantidadIngresada() +
            ", facturaProveedor='" + getFacturaProveedor() + "'" +
            ", producto=" + getProducto() +
            ", sitio=" + getSitio() +
            "}";
    }
}
