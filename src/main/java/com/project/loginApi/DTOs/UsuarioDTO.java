package com.project.loginApi.DTOs;

public record UsuarioDTO(String nome, String cpf, String login, String senha,
                         String telefone, Boolean isAtivo, String email) {
}
