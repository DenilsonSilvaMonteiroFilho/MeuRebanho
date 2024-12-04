package com.project.loginApi.DTOs;

public record AuthenticationDTO(String login, String senha, String email){
    public static record LoginResponseDTO(String token) {
    }
}
