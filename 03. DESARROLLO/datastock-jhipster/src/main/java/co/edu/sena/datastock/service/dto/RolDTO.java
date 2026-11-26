package co.edu.sena.datastock.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.datastock.domain.Rol} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RolDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 30)
    private String nombreRol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RolDTO)) {
            return false;
        }

        RolDTO rolDTO = (RolDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, rolDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RolDTO{" +
            "id=" + getId() +
            ", nombreRol='" + getNombreRol() + "'" +
            "}";
    }
}
