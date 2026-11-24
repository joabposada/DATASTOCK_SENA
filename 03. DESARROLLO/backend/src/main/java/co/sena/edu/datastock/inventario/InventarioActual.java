package co.sena.edu.datastock.inventario;

import co.sena.edu.datastock.catalogo.Producto;
import co.sena.edu.datastock.infraestructura.Sitio;
import jakarta.persistence.*;

@Entity
@Table(name = "inventario_actual")
public class InventarioActual {

    // incrusto la llave compuesta formada por producto y sitio
    @EmbeddedId
    private InventarioActualId id;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // enlazo el objeto producto con el atributo 'idproducto' de mi llave compuesta
    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    // enlazo el objeto sitio con el atributo 'idsitio' de mi llave compuesta
    @ManyToOne
    @MapsId("idSitio")
    @JoinColumn(name = "id_sitio", nullable = false)
    private Sitio sitio;

    @Column(name = "stock_disponible", nullable = false)
    private Integer stockDisponible;

}