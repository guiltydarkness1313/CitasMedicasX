package com.shibuyaxpress.citasmedicasx.models;

/**
 * Created by paulf on 03-Dec-17.
 */

public class Pacientes {
    private int id;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String tipo_doc;
    private int num_doc;
    private String fecha_nacimiento;
    private String nacionalidad;
    private String imagen_perfil;
    private String direccion;
    private String cod_postal;
    private String ciudad;
    private String provincia;
    private int telefono;
    private int celular;
    private String correo;
    private String genero;
    private int usuario_id;

    private static Pacientes _INSTANCE=null;

    public static Pacientes getInstance() {
        if(_INSTANCE==null){
            _INSTANCE=new Pacientes();
        }
        return _INSTANCE;
    }

    private Pacientes() {
    }


    public Pacientes(int id, String nombre, String apellido_paterno, String apellido_materno, String tipo_doc, int num_doc, String fecha_nacimiento, String nacionalidad, String imagen_perfil, String direccion, String cod_postal, String ciudad, String provincia, int telefono, int celular, String correo, String genero, int usuario_id) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.tipo_doc = tipo_doc;
        this.num_doc = num_doc;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.imagen_perfil = imagen_perfil;
        this.direccion = direccion;
        this.cod_postal = cod_postal;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.celular = celular;
        this.correo = correo;
        this.genero = genero;
        this.usuario_id = usuario_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public int getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(int num_doc) {
        this.num_doc = num_doc;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getImagen_perfil() {
        return imagen_perfil;
    }

    public void setImagen_perfil(String imagen_perfil) {
        this.imagen_perfil = imagen_perfil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
}
