package co.edu.sena.datastock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LoteEntrada.
 */
@Entity
@Table(name = "lote_entrada")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoteEntrada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @NotNull
    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @NotNull
    @Min(value = 1)
    @Column(name = "cantidad_ingresada", nullable = false)
    private Integer cantidadIngresada;

    @NotNull
    @Size(max = 50)
    @Column(name = "factura_proveedor", length = 50, nullable = false)
    private String facturaProveedor;

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

    public LoteEntrada id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaIngreso() {
        return this.fechaIngreso;
    }

    public LoteEntrada fechaIngreso(LocalDate fechaIngreso) {
        this.setFechaIngreso(fechaIngreso);
        return this;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public LoteEntrada fechaVencimiento(LocalDate fechaVencimiento) {
        this.setFechaVencimiento(fechaVencimiento);
        return this;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getCantidadIngresada() {
        return this.cantidadIngresada;
    }

    public LoteEntrada cantidadIngresada(Integer cantidadIngresada) {
        this.setCantidadIngresada(cantidadIngresada);
        return this;
    }

    public void setCantidadIngresada(Integer cantidadIngresada) {
        this.cantidadIngresada = cantidadIngresada;
    }

    public String getFacturaProveedor() {
        return this.facturaProveedor;
    }

    public LoteEntrada facturaProveedor(String facturaProveedor) {
        this.setFacturaProveedor(facturaProveedor);
        return this;
    }

    public void setFacturaProveedor(String facturaProveedor) {
        this.facturaProveedor = facturaProveedor;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LoteEntrada producto(Producto producto) {
        this.setProducto(producto);
        return this;
    }

    public Sitio getSitio() {
        return this.sitio;
    }

    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    public LoteEntrada sitio(Sitio sitio) {
        this.setSitio(sitio);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoteEntrada)) {
            return false;
        }
        return getId() != null && getId().equals(((LoteEntrada) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoteEntrada{" +
            "id=" + getId() +
            ", fechaIngreso='" + getFechaIngreso() + "'" +
            ", fechaVencimiento='" + getFechaVencimiento() + "'" +
            ", cantidadIngresada=" + getCantidadIngresada() +
            ", facturaProveedor='" + getFacturaProveedor() + "'" +
            "}";
    }
}
