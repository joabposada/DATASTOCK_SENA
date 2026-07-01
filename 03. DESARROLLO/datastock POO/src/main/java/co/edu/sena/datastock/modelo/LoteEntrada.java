package co.edu.sena.datastock.modelo;

import java.time.LocalDate;

public class LoteEntrada {
    private int idLote;
    private Producto producto;
    private Sitio sitio;
    private LocalDate fechaIngreso;
    private LocalDate fechaVencimiento;
    private int cantidadIngresada;
    private String facturaProveedor;

    public LoteEntrada() {}

    public LoteEntrada(int idLote, Producto producto, Sitio sitio, LocalDate fechaIngreso, LocalDate fechaVencimiento, int cantidadIngresada, String facturaProveedor) {
        this.idLote = idLote;
        this.producto = producto;
        this.sitio = sitio;
        this.fechaIngreso = fechaIngreso;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidadIngresada = cantidadIngresada;
        this.facturaProveedor = facturaProveedor;
    }

    public int getIdLote() { return idLote; }
    public void setIdLote(int idLote) { this.idLote = idLote; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Sitio getSitio() { return sitio; }
    public void setSitio(Sitio sitio) { this.sitio = sitio; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public int getCantidadIngresada() { return cantidadIngresada; }
    public void setCantidadIngresada(int cantidadIngresada) { this.cantidadIngresada = cantidadIngresada; }

    public String getFacturaProveedor() { return facturaProveedor; }
    public void setFacturaProveedor(String facturaProveedor) { this.facturaProveedor = facturaProveedor; }


    public int getIdProducto() {
        return producto != null ? producto.getIdProducto() : 0;
    }

    public int getIdSitio() {
        return sitio != null ? sitio.getIdSitio() : 0;
    }
}