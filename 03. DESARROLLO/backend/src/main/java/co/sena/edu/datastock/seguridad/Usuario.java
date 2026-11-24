package co.sena.edu.datastock.seguridad;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "numero_documento", length = 11, unique = true, nullable = false)
    private String numeroDocumento;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String apellido;

    @Column(length = 15, nullable = false)
    private String celular;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Column(length = 100, unique = true, nullable = false)
    private String correo;

    @Column(length = 255, nullable = false)
    private String password;

}
