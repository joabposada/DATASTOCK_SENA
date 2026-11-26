package co.edu.sena.datastock.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.datastock.domain.HistorialMovimiento} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HistorialMovimientoDTO implements Serializable {

    private Long id;

    private Integer idSitioDestino;

    @NotNull
    @Size(max = 30)
    private String tipoMovimiento;

    @NotNull
    @Min(value = 1)
    private Integer cantidad;

    @NotNull
    private Instant fechaHora;

    @NotNull
    private ProductoDTO producto;

    @NotNull
    private SitioDTO sitio;

    @NotNull
    private UsuarioDTO usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdSitioDestino() {
        return idSitioDestino;
    }

    public void setIdSitioDestino(Integer idSitioDestino) {
        this.idSitioDestino = idSitioDestino;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Instant getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Instant fechaHora) {
        this.fechaHora = fechaHora;
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

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistorialMovimientoDTO)) {
            return false;
        }

        HistorialMovimientoDTO historialMovimientoDTO = (HistorialMovimientoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, historialMovimientoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistorialMovimientoDTO{" +
            "id=" + getId() +
            ", idSitioDestino=" + getIdSitioDestino() +
            ", tipoMovimiento='" + getTipoMovimiento() + "'" +
            ", cantidad=" + getCantidad() +
            ", fechaHora='" + getFechaHora() + "'" +
            ", producto=" + getProducto() +
            ", sitio=" + getSitio() +
            ", usuario=" + getUsuario() +
            "}";
    }
}
