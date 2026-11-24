//package inventario y movimiento
package co.sena.edu.datastock.inventario;

import co.sena.edu.datastock.catalogo.Producto;
import co.sena.edu.datastock.infraestructura.Sitio;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lote_entrada")
public class LoteEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote")
    private Integer idLote;

    // Traigo el producto desde el paquete catalogo
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    // Traigo el sitio desde el paquete infraestructura
    @ManyToOne
    @JoinColumn(name = "id_sitio", nullable = false)
    private Sitio sitio;

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "cantidad_ingresada", nullable = false)
    private Integer cantidadIngresada;

    @Column(name = "factura_proveedor", length = 50, nullable = false)
    private String facturaProveedor;
    
}