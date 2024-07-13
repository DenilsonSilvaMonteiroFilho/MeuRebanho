package com.project.loginApi.entities.Usuario;

public enum PapelUsuario {
    ADMIN("admin"),
    USUARIO("usuario");

    private String role;

    PapelUsuario(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
