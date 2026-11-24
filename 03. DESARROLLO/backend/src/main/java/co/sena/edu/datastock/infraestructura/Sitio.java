package co.sena.edu.datastock.infraestructura;

import jakarta.persistence.*;

@Entity
@Table(name = "sitio")
public class Sitio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sitio")
    private Integer idSitio;

    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    @Column(name = "nombre_sitio", length = 100, nullable = false)
    private String nombreSitio;

    @Column(name = "tipo_sitio", length = 50, nullable = false)
    private String tipoSitio;

    @Column(length = 150, nullable = false)
    private String direccion;

}