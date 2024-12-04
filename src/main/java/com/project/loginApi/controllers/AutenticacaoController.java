package com.project.loginApi.controllers;

import com.project.loginApi.DTOs.AuthenticationDTO;
import com.project.loginApi.DTOs.UsuarioDTO;
import com.project.loginApi.servicies.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        return contaService.login(data);
    }

    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody UsuarioDTO usuarioDTO){
        return contaService.cadastro(usuarioDTO);
    }
}
