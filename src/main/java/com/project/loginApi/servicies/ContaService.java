package com.project.loginApi.servicies;

import com.project.loginApi.DTOs.AuthenticationDTO;
import com.project.loginApi.DTOs.CadastroDTO;
import com.project.loginApi.DTOs.ResponseDTO;
import com.project.loginApi.entities.Usuario.Usuario;
import com.project.loginApi.infra.security.TokenService;
import com.project.loginApi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity cadastro(CadastroDTO cadastroDTO){
        try{
            Optional<Usuario> usuario = this.usuarioRepository.findByEmail(cadastroDTO.email());

            if(usuario.isEmpty()) {
                Usuario novoUsuario = new Usuario();

                novoUsuario.setSenha(passwordEncoder.encode(cadastroDTO.senha()));
                novoUsuario.setEmail(cadastroDTO.email());
                novoUsuario.setNome(cadastroDTO.nome());

                this.usuarioRepository.save(novoUsuario);

                String token = this.tokenService.gerarToken(novoUsuario);
                return ResponseEntity.ok(new ResponseDTO(novoUsuario.getNome(), token));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
    public ResponseEntity login(AuthenticationDTO data){
        Usuario ususario = this.usuarioRepository.findByEmail(data.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(data.senha(), ususario.getPassword())) {
            String token = this.tokenService.gerarToken(ususario);
            return ResponseEntity.ok(new ResponseDTO(ususario.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
