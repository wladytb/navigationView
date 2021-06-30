package com.wladytb.otro_deber.modelo;

public class user {
    String usuario,password,photo,fullName,rol;

    public user() {
    }

    public user(String usuario, String password, String photo, String fullName, String rol) {
        this.usuario = usuario;
        this.password = password;
        this.photo = photo;
        this.fullName = fullName;
        this.rol = rol;
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
