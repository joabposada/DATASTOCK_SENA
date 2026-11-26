package co.edu.sena.datastock.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Sitio.
 */
@Entity
@Table(name = "sitio")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Sitio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "nombre_sitio", length = 60, nullable = false)
    private String nombreSitio;

    @NotNull
    @Size(max = 30)
    @Column(name = "tipo_sitio", length = 30, nullable = false)
    private String tipoSitio;

    @NotNull
    @Size(max = 120)
    @Column(name = "direccion", length = 120, nullable = false)
    private String direccion;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Sitio id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSitio() {
        return this.nombreSitio;
    }

    public Sitio nombreSitio(String nombreSitio) {
        this.setNombreSitio(nombreSitio);
        return this;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public String getTipoSitio() {
        return this.tipoSitio;
    }

    public Sitio tipoSitio(String tipoSitio) {
        this.setTipoSitio(tipoSitio);
        return this;
    }

    public void setTipoSitio(String tipoSitio) {
        this.tipoSitio = tipoSitio;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public Sitio direccion(String direccion) {
        this.setDireccion(direccion);
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sitio)) {
            return false;
        }
        return getId() != null && getId().equals(((Sitio) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sitio{" +
            "id=" + getId() +
            ", nombreSitio='" + getNombreSitio() + "'" +
            ", tipoSitio='" + getTipoSitio() + "'" +
            ", direccion='" + getDireccion() + "'" +
            "}";
    }
}
