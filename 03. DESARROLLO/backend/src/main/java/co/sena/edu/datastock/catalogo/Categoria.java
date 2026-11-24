package co.sena.edu.datastock.catalogo;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "nombre_categoria", length = 50, nullable = false)
    private String nombreCategoria;

}
