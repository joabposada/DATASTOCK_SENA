package co.sena.edu.datastock.catalogo;

import jakarta.persistence.*;

@Entity
@Table(name = "marca")
public class Marca {

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Integer idMarca;

    @Column(name = "nombre_marca", length = 50, nullable = false)
    private String nombreMarca;

}
