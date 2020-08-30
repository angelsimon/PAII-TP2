package com.example.tp2;

import java.io.Serializable;

public class Contacto implements Serializable {

    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String tipoTel;
    private String email;
    private String tipoEmail;
    private String direccion;
    private String fechanac;
    private String nivelEstudio;
    private String intereses;
    private Boolean recibeInfo;

    public Contacto(String nombre, String apellido, String telefono, String tipoTel, String email, String tipoEmail, String direccion, String fechanac, String nivelEstudio, String intereses, Boolean recibeInfo)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.tipoTel = tipoTel;
        this.email = email;
        this.tipoEmail = tipoEmail;
        this.direccion = direccion;
        this.fechanac = fechanac;
        this.nivelEstudio = nivelEstudio;
        this.intereses = intereses;
        this.recibeInfo = recibeInfo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoTel() {
        return tipoTel;
    }

    public void setTipoTel(String tipoTel) {
        this.tipoTel = tipoTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(String tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getIntereses() {
        return intereses;
    }

    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }

    public Boolean getRecibeInfo() {
        return recibeInfo;
    }

    public void setRecibeInfo(Boolean recibeInfo) {
        this.recibeInfo = recibeInfo;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipoTel='" + tipoTel + '\'' +
                ", email='" + email + '\'' +
                ", tipoEmail='" + tipoEmail + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechanac='" + fechanac + '\'' +
                ", nivelEstudio='" + nivelEstudio + '\'' +
                ", intereses='" + intereses + '\'' +
                ", recibeInfo=" + recibeInfo +
                '}';
    }

    public String Listado()
    {
        return nombre + " " + apellido + " " + " - " + email;
    }
}
