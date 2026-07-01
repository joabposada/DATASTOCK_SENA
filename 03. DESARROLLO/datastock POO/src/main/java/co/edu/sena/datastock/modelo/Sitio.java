package co.edu.sena.datastock.modelo;

public class Sitio {
    private int idSitio;
    private String nombreSitio;
    private String tipoSitio;
    private String direccion;

    public Sitio() {}

    public Sitio(int idSitio, String nombreSitio, String tipoSitio, String direccion) {
        this.idSitio = idSitio;
        this.nombreSitio = nombreSitio;
        this.tipoSitio = tipoSitio;
        this.direccion = direccion;
    }

    public int getIdSitio() { return idSitio; }
    public void setIdSitio(int idSitio) { this.idSitio = idSitio; }

    public String getNombreSitio() { return nombreSitio; }
    public void setNombreSitio(String nombreSitio) { this.nombreSitio = nombreSitio; }

    public String getTipoSitio() { return tipoSitio; }
    public void setTipoSitio(String tipoSitio) { this.tipoSitio = tipoSitio; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}