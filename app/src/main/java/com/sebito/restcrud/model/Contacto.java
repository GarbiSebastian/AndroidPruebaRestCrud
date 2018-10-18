package com.sebito.restcrud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contacto {

    @SerializedName("id")
    @Expose
    protected int id;

    @SerializedName("nombre")
    @Expose
    protected String nombre;

    @SerializedName("apellido")
    @Expose
    protected String apellido;

    @SerializedName("telefono")
    @Expose
    protected String telefono;

    @SerializedName("email")
    @Expose
    protected String email;

    public Contacto() {

    }

    public Contacto(int id, String nombre, String apellido, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Contacto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public Contacto setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public Contacto setTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contacto setEmail(String email) {
        this.email = email;
        return this;
    }
}
