package com.example.pauli.finallogin;

import java.sql.Date;

public class Tareas {
    private Integer idUS;
    private String nombreUs;
    private Integer idUserCreador;
    private Integer idUserEditor;
    private String estado;
    private String fecha;
    private String fechaFin;
    private Integer idSprint;

    public Tareas(){
    }


    public Tareas(Integer idUS, String nombreUs, Integer idUserCreador, Integer
            idUserEditor, String estado, String fecha, String fechaFin, Integer idSprint) {
        this.idSprint = idSprint;
        this.nombreUs = nombreUs;
        this.idUserCreador= idUserCreador;
        this.idUserEditor = idUserEditor;
        this.estado = estado;
        this.fecha = fecha;
        this.fechaFin = fechaFin;
        this.idUS = idUS;
    }



    public Integer getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public String getNombreUs() {
        return nombreUs;
    }

    public void setNombreUs(String nombreUs) {
        this.nombreUs = nombreUs;
    }

    public Integer getIdUserCreador() {
        return idUserCreador;
    }

    public void setIdUserCreador(Integer idUserCreador) {
        this.idUserCreador = idUserCreador;
    }

    public Integer getIdUserEditor() {
        return idUserEditor;
    }

    public void setIdUserEditor(Integer idUserEditor) {
        this.idUserEditor = idUserEditor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fecha_fin) {
        this.fechaFin = fecha_fin;
    }

    public Integer getIdUS() {
        return idUS;
    }

    public void setIdUS(Integer idUS) {
        this.idUS = idUS;
    }


}
