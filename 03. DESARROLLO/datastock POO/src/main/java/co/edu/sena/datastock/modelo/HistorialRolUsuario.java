package co.edu.sena.datastock.modelo;

import java.time.LocalDate;

public class HistorialRolUsuario {
    private int id;
    private Usuario usuario;
    private Rol rol;
    private LocalDate fechaAsignacion;
    private EstadoAsignacion estado;

    public HistorialRolUsuario() {}

    public HistorialRolUsuario(int id, Usuario usuario, Rol rol, LocalDate fechaAsignacion, EstadoAsignacion estado) {
        this.id = id;
        this.usuario = usuario;
        this.rol = rol;
        this.fechaAsignacion = fechaAsignacion;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public LocalDate getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDate fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }

    public EstadoAsignacion getEstado() { return estado; }
    public void setEstado(EstadoAsignacion estado) { this.estado = estado; }
}