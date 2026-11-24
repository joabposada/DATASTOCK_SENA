package co.sena.edu.datastock.seguridad;

import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {

    // Uso GenerationType.IDENTITY para que PostgreSQL maneje el autoincremento de la llave sustituta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Column(name = "nombre_rol", length = 30, nullable = false)
    private String nombreRol;

}
