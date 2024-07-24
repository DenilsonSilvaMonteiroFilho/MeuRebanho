package com.project.loginApi.entities.Usuario;

public enum PapelUsuario {
    ADMIN("ADMIN"),
    USUARIO("USUARIO");

    private String role;

    PapelUsuario(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
