package co.sena.edu.datastock.inventario;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// Uso @Embeddable porque esta clase representa la llave compuesta de inventario_actual
@Embeddable
public class InventarioActualId implements Serializable {

    @Column(name = "id_producto")
    private Integer idProducto;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InventarioActualId that = (InventarioActualId) o;
        return Objects.equals(idProducto, that.idProducto) && Objects.equals(idSitio, that.idSitio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idSitio);
    }

    public Integer getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(Integer idSitio) {
        this.idSitio = idSitio;
    }

    @Column(name = "id_sitio")
    private Integer idSitio;

}
