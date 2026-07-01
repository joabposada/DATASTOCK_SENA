package co.edu.sena.datastock.modelo;

import java.time.LocalDateTime;

public class HistorialMovimiento {
    private int idMovimiento;
    private Producto producto;
    private Sitio sitioOrigen;
    private Sitio sitioDestino;
    private TipoMovimiento tipoMovimiento;
    private int cantidad;
    private LocalDateTime fechaHora;

    public HistorialMovimiento() {}

    public HistorialMovimiento(int idMovimiento, Producto producto, Sitio sitioOrigen, Sitio sitioDestino, TipoMovimiento tipoMovimiento, int cantidad, LocalDateTime fechaHora) {
        this.idMovimiento = idMovimiento;
        this.producto = producto;
        this.sitioOrigen = sitioOrigen;
        this.sitioDestino = sitioDestino;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }

    public int getIdMovimiento() { return idMovimiento; }
    public void setIdMovimiento(int idMovimiento) { this.idMovimiento = idMovimiento; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Sitio getSitioOrigen() { return sitioOrigen; }
    public void setSitioOrigen(Sitio sitioOrigen) { this.sitioOrigen = sitioOrigen; }

    public Sitio getSitioDestino() { return sitioDestino; }
    public void setSitioDestino(Sitio sitioDestino) { this.sitioDestino = sitioDestino; }

    public TipoMovimiento getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }


    public int getIdProducto() { return producto != null ? producto.getIdProducto() : 0; }
    public int getIdSitioOrigen() { return sitioOrigen != null ? sitioOrigen.getIdSitio() : 0; }
    public int getIdSitioDestino() { return sitioDestino != null ? sitioDestino.getIdSitio() : 0; }
}