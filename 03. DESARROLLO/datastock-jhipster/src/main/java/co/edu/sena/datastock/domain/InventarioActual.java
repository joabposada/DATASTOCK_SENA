package co.edu.sena.datastock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InventarioActual.
 */
@Entity
@Table(name = "inventario_actual")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InventarioActual implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "stock_disponible", nullable = false)
    private Integer stockDisponible;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "categoria", "marca" }, allowSetters = true)
    private Producto producto;

    @ManyToOne(optional = false)
    @NotNull
    private Sitio sitio;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InventarioActual id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStockDisponible() {
        return this.stockDisponible;
    }

    public InventarioActual stockDisponible(Integer stockDisponible) {
        this.setStockDisponible(stockDisponible);
        return this;
    }

    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public InventarioActual producto(Producto producto) {
        this.setProducto(producto);
        return this;
    }

    public Sitio getSitio() {
        return this.sitio;
    }

    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    public InventarioActual sitio(Sitio sitio) {
        this.setSitio(sitio);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InventarioActual)) {
            return false;
        }
        return getId() != null && getId().equals(((InventarioActual) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InventarioActual{" +
            "id=" + getId() +
            ", stockDisponible=" + getStockDisponible() +
            "}";
    }
}
