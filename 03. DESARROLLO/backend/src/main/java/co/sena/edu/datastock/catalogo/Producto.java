package co.sena.edu.datastock.catalogo;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "nombre_producto", length = 100, nullable = false)
    private String nombreProducto;

    @Column(length = 50, nullable = false)
    private String presentacion;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Conecto muchos productos hacia una misma categoría
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    // Conecto muchos productos hacia una misma marca
    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca marca;
}
