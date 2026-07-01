package co.edu.sena.datastock.modelo;

public class InventarioActual {
    private Producto producto;
    private Sitio sitio;
    private int stockDisponible;

    public InventarioActual() {}

    public InventarioActual(Producto producto, Sitio sitio, int stockDisponible) {
        this.producto = producto;
        this.sitio = sitio;
        this.stockDisponible = stockDisponible;
    }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Sitio getSitio() { return sitio; }
    public void setSitio(Sitio sitio) { this.sitio = sitio; }

    public int getStockDisponible() { return stockDisponible; }
    public void setStockDisponible(int stockDisponible) { this.stockDisponible = stockDisponible; }


    public int getIdProducto() {
        return producto != null ? producto.getIdProducto() : 0;
    }

    public int getIdSitio() {
        return sitio != null ? sitio.getIdSitio() : 0;
    }
}