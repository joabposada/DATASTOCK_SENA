package co.edu.sena.datastock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HistorialMovimiento.
 */
@Entity
@Table(name = "historial_movimiento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HistorialMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_sitio_destino")
    private Integer idSitioDestino;

    @NotNull
    @Size(max = 30)
    @Column(name = "tipo_movimiento", length = 30, nullable = false)
    private String tipoMovimiento;

    @NotNull
    @Min(value = 1)
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull
    @Column(name = "fecha_hora", nullable = false)
    private Instant fechaHora;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "categoria", "marca" }, allowSetters = true)
    private Producto producto;

    @ManyToOne(optional = false)
    @NotNull
    private Sitio sitio;

    @ManyToOne(optional = false)
    @NotNull
    private Usuario usuario;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HistorialMovimiento id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdSitioDestino() {
        return this.idSitioDestino;
    }

    public HistorialMovimiento idSitioDestino(Integer idSitioDestino) {
        this.setIdSitioDestino(idSitioDestino);
        return this;
    }

    public void setIdSitioDestino(Integer idSitioDestino) {
        this.idSitioDestino = idSitioDestino;
    }

    public String getTipoMovimiento() {
        return this.tipoMovimiento;
    }

    public HistorialMovimiento tipoMovimiento(String tipoMovimiento) {
        this.setTipoMovimiento(tipoMovimiento);
        return this;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public HistorialMovimiento cantidad(Integer cantidad) {
        this.setCantidad(cantidad);
        return this;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Instant getFechaHora() {
        return this.fechaHora;
    }

    public HistorialMovimiento fechaHora(Instant fechaHora) {
        this.setFechaHora(fechaHora);
        return this;
    }

    public void setFechaHora(Instant fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public HistorialMovimiento producto(Producto producto) {
        this.setProducto(producto);
        return this;
    }

    public Sitio getSitio() {
        return this.sitio;
    }

    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    public HistorialMovimiento sitio(Sitio sitio) {
        this.setSitio(sitio);
        return this;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public HistorialMovimiento usuario(Usuario usuario) {
        this.setUsuario(usuario);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistorialMovimiento)) {
            return false;
        }
        return getId() != null && getId().equals(((HistorialMovimiento) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistorialMovimiento{" +
            "id=" + getId() +
            ", idSitioDestino=" + getIdSitioDestino() +
            ", tipoMovimiento='" + getTipoMovimiento() + "'" +
            ", cantidad=" + getCantidad() +
            ", fechaHora='" + getFechaHora() + "'" +
            "}";
    }
}
