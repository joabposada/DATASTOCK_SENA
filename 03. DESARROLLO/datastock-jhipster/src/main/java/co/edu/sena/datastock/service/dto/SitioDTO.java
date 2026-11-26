package co.edu.sena.datastock.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.datastock.domain.Sitio} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SitioDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 60)
    private String nombreSitio;

    @NotNull
    @Size(max = 30)
    private String tipoSitio;

    @NotNull
    @Size(max = 120)
    private String direccion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public String getTipoSitio() {
        return tipoSitio;
    }

    public void setTipoSitio(String tipoSitio) {
        this.tipoSitio = tipoSitio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SitioDTO)) {
            return false;
        }

        SitioDTO sitioDTO = (SitioDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sitioDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SitioDTO{" +
            "id=" + getId() +
            ", nombreSitio='" + getNombreSitio() + "'" +
            ", tipoSitio='" + getTipoSitio() + "'" +
            ", direccion='" + getDireccion() + "'" +
            "}";
    }
}
