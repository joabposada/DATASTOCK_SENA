package co.sena.edu.datastock.inventario;

import co.sena.edu.datastock.catalogo.Producto;
import co.sena.edu.datastock.infraestructura.Sitio;
import co.sena.edu.datastock.seguridad.Usuario;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_movimiento")
public class HistorialMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_sitio", nullable = false)
    private Sitio sitio;

    public Integer getIdSitioDestino() {
        return idSitioDestino;
    }

    public void setIdSitioDestino(Integer idSitioDestino) {
        this.idSitioDestino = idSitioDestino;
    }

    @Column(name = "id_sitio_destino")
    private Integer idSitioDestino;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "tipo_movimiento", length = 30, nullable = false)
    private String tipoMovimiento;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

}
