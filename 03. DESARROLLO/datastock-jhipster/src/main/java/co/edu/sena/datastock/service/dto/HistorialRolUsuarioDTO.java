package co.edu.sena.datastock.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.datastock.domain.HistorialRolUsuario} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HistorialRolUsuarioDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate fechaAsignacion;

    @NotNull
    @Size(max = 15)
    private String estado;

    @NotNull
    private UsuarioDTO usuario;

    @NotNull
    private RolDTO rol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public RolDTO getRol() {
        return rol;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistorialRolUsuarioDTO)) {
            return false;
        }

        HistorialRolUsuarioDTO historialRolUsuarioDTO = (HistorialRolUsuarioDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, historialRolUsuarioDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistorialRolUsuarioDTO{" +
            "id=" + getId() +
            ", fechaAsignacion='" + getFechaAsignacion() + "'" +
            ", estado='" + getEstado() + "'" +
            ", usuario=" + getUsuario() +
            ", rol=" + getRol() +
            "}";
    }
}
