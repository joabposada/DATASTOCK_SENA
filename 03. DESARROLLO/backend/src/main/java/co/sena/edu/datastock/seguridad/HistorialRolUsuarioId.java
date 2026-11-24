package co.sena.edu.datastock.seguridad;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// Uso @Embeddable porque esta clase no es una tabla, sino la llave primaria compuesta de historial_rol_usuario
@Embeddable
public class HistorialRolUsuarioId implements Serializable {

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_rol")
    private Integer idRol;

    public HistorialRolUsuarioId() {
    }

    public HistorialRolUsuarioId(Integer idUsuario, Integer idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Hibernate exige equals y hashCode en llaves compuestas para poder comparar registros en memoria
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialRolUsuarioId that = (HistorialRolUsuarioId) o;
        return Objects.equals(idUsuario, that.idUsuario) && Objects.equals(idRol, that.idRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idRol);
    }
}
