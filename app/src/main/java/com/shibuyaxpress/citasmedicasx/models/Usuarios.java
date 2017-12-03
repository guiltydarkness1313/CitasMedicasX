package com.shibuyaxpress.citasmedicasx.models;

/**
 * Created by paulf on 24-Oct-17.
 */

public class Usuarios {
    private String id;
    private String nombre;
    private String clave;
    private String tipo;
    private String registro_fecha;

    public Usuarios() {
    }

    public Usuarios(String id, String nombre, String clave, String tipo, String registro_fecha) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.tipo = tipo;
        this.registro_fecha = registro_fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRegistro_fecha() {
        return registro_fecha;
    }

    public void setRegistro_fecha(String registro_fecha) {
        this.registro_fecha = registro_fecha;
    }
}
