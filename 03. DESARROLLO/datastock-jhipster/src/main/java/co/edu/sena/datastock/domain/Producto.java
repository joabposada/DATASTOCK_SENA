package co.edu.sena.datastock.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Producto.
 */
@Entity
@Table(name = "producto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "nombre_producto", length = 100, nullable = false)
    private String nombreProducto;

    @NotNull
    @Size(max = 50)
    @Column(name = "presentacion", length = 50, nullable = false)
    private String presentacion;

    @ManyToOne(optional = false)
    @NotNull
    private Categoria categoria;

    @ManyToOne(optional = false)
    @NotNull
    private Marca marca;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Producto id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public Producto nombreProducto(String nombreProducto) {
        this.setNombreProducto(nombreProducto);
        return this;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPresentacion() {
        return this.presentacion;
    }

    public Producto presentacion(String presentacion) {
        this.setPresentacion(presentacion);
        return this;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Producto categoria(Categoria categoria) {
        this.setCategoria(categoria);
        return this;
    }

    public Marca getMarca() {
        return this.marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Producto marca(Marca marca) {
        this.setMarca(marca);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Producto)) {
            return false;
        }
        return getId() != null && getId().equals(((Producto) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Producto{" +
            "id=" + getId() +
            ", nombreProducto='" + getNombreProducto() + "'" +
            ", presentacion='" + getPresentacion() + "'" +
            "}";
    }
}
