package co.edu.sena.datastock.modelo;

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private String presentacion;
    private Categoria categoria;
    private Marca marca;

    public Producto() {}

    public Producto(int idProducto, String nombreProducto, String presentacion, Categoria categoria, Marca marca) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.presentacion = presentacion;
        this.categoria = categoria;
        this.marca = marca;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getPresentacion() { return presentacion; }
    public void setPresentacion(String presentacion) { this.presentacion = presentacion; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }


    public int getIdCategoria() {
        return categoria != null ? categoria.getIdCategoria() : 0;
    }

    public int getIdMarca() {
        return marca != null ? marca.getIdMarca() : 0;
    }
}