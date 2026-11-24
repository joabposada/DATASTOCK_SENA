// Package seguridad y usuario
package co.sena.edu.datastock.seguridad;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "historial_rol_usuario")
public class HistorialRolUsuario {

    // Incrusto la llave compuesta que armamos arriba
    @EmbeddedId
    private HistorialRolUsuarioId id;

    // @MapsId conecta la llave foránea de Usuario directamente con el campo idUsuario de la llave compuesta
    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // Lo mismo aquí, enlazo el objeto rol con el campo idrol del @EmbeddedId
    @ManyToOne
    @MapsId("idRol")
    @JoinColumn(name = "id_rol")
    private Rol rol;

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDate fechaAsignacion;

    @Column(length = 15, nullable = false)
    private String estado;

}