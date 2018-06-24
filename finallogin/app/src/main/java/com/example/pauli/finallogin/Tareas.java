package com.example.pauli.finallogin;

import java.sql.Date;

public class Tareas {
    private Integer idSprint;
    private Integer duracion;
    private String nombreSprint;
    private String fecha;
    private Integer idUsuario;

    public Tareas(){
    }

    public Tareas(Integer idSprint, Integer duracion, String nombreSprint, String fecha, int idUsuario) {
        this.idSprint = idSprint;
        this.duracion = duracion;
        this.nombreSprint = nombreSprint;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    public Integer getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getNombreSprint() {
        return nombreSprint;
    }

    public void setNombreSprint(String nombreSprint) {
        this.nombreSprint = nombreSprint;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
