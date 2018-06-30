package com.example.pauli.finallogin;

import java.sql.Date;

public class Tareas {
    private Integer idUS;
    private String nombreUS;
    private Integer idUsuarioCreador;
    private Integer idUsuarioEditor;
    private String estado;
    private String fecha;
    private String fecha_fin;
    private Integer idSprint;

    public Tareas(){
    }


    public Tareas(Integer idUS, String nombreUS, Integer idUsuarioCreador, Integer
    idUsuarioEditor, String estado, String fecha, String fecha_fin, Integer idSprint) {
        this.idSprint = idSprint;
        this.nombreUS = nombreUS;
        this.idUsuarioCreador= idUsuarioCreador;
        this.idUsuarioEditor = idUsuarioEditor;
        this.estado = estado;
        this.fecha = fecha;
        this.fecha_fin = fecha_fin;
        this.idUS = idUS;
    }



    public Integer getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public String getNombreUS() {
        return nombreUS;
    }

    public void setNombreUS(String nombreUS) {
        this.nombreUS = nombreUS;
    }

    public Integer getIdUsuarioCreador() {
        return idUsuarioCreador;
    }

    public void setIdUsuarioCreador(Integer idUsuarioCreador) {
        this.idUsuarioCreador = idUsuarioCreador;
    }

    public Integer getIdUsuarioEditor() {
        return idUsuarioEditor;
    }

    public void setIdUsuarioEditor(Integer idUsuarioEditor) {
        this.idUsuarioEditor = idUsuarioEditor;
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
        return fecha_fin;
    }

    public void setFechaFin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Integer getIdUS() {
        return idUS;
    }

    public void setIdUS(Integer idUS) {
        this.idUS = idUS;
    }


}
