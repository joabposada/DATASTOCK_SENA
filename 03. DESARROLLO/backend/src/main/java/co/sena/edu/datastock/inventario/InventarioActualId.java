package co.sena.edu.datastock.inventario;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InventarioActualId implements Serializable {

    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "id_sitio")
    private Integer idSitio;

    public InventarioActualId() {
    }

    public InventarioActualId(Integer idProducto, Integer idSitio) {
        this.idProducto = idProducto;
        this.idSitio = idSitio;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(Integer idSitio) {
        this.idSitio = idSitio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventarioActualId that = (InventarioActualId) o;
        return Objects.equals(idProducto, that.idProducto) && Objects.equals(idSitio, that.idSitio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idSitio);
    }
}