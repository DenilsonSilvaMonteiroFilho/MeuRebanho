package com.project.loginApi.DTOs;

public record AuthenticationDTO( String email, String senha){
    public static record LoginResponpseDTO(String token) {
    }
}
