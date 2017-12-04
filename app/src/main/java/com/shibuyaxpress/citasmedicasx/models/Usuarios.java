package com.shibuyaxpress.citasmedicasx.models;

/**
 * Created by paulf on 24-Oct-17.
 */

public class Usuarios {
    private String id;
    private String username;
    private String password;
    private String type;
    private String reg_date;

    private static Usuarios _INSTANCE=null;

    public static Usuarios getInstance() {
        if(_INSTANCE==null){
            _INSTANCE=new Usuarios();
        }
        return _INSTANCE;
    }

    private Usuarios() {
    }

    public Usuarios(String id, String username, String password, String type, String reg_date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.reg_date = reg_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return username;
    }

    public void setNombre(String username) {
        this.username = username;
    }

    public String getClave() {
        return password;
    }

    public void setClave(String password) {
        this.password = password;
    }

    public String getTipo() {
        return type;
    }

    public void setTipo(String type) {
        this.type = type;
    }

    public String getRegistro_fecha() {
        return reg_date;
    }

    public void setRegistro_fecha(String reg_date) {
        this.reg_date = reg_date;
    }
}
