package co.sena.edu.datastock.inventario;

import co.sena.edu.datastock.catalogo.Producto;
import co.sena.edu.datastock.infraestructura.Sitio;
import jakarta.persistence.*;

@Entity
@Table(name = "inventario_actual")
public class InventarioActual {

    @EmbeddedId
    private InventarioActualId id;

    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @MapsId("idSitio")
    @JoinColumn(name = "id_sitio", nullable = false)
    private Sitio sitio;

    @Column(name = "stock_disponible", nullable = false)
    private Integer stockDisponible;

    public InventarioActualId getId() {
        return id;
    }

    public void setId(InventarioActualId id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Sitio getSitio() {
        return sitio;
    }

    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    public Integer getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }
}