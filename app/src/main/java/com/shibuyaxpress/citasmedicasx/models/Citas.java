package com.shibuyaxpress.citasmedicasx.models;

/**
 * Created by paulf on 04-Dec-17.
 */

public class Citas {
    private String id;
    private String  estado;
    private String fecha;
    private String descripcion;
    private String  medico_id;
    private String paciente_id;

    public Citas() {
    }

    public Citas(String id, String estado, String fecha, String descripcion, String medico_id, String paciente_id) {
        this.id = id;
        this.estado = estado;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.medico_id = medico_id;
        this.paciente_id = paciente_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMedico_id() {
        return medico_id;
    }

    public void setMedico_id(String medico_id) {
        this.medico_id = medico_id;
    }

    public String getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(String paciente_id) {
        this.paciente_id = paciente_id;
    }
}
