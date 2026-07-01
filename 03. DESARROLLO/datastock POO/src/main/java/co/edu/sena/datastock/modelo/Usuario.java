package co.edu.sena.datastock.modelo;

public class Usuario {
    private int idUsuario;
    private String numeroDocumento;
    private String nombres;
    private String apellido;
    private String celular;
    private String correo;
    private String password;

    public Usuario() {
        // para que sonar no moleste
    }

    public Usuario(int idUsuario, String numeroDocumento, String nombres, String apellido, String celular, String correo, String password) {
        this.idUsuario = idUsuario;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellido = apellido;
        this.celular = celular;
        this.correo = correo;
        this.password = password;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}