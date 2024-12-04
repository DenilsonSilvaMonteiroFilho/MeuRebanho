package com.project.loginApi.servicies;

import com.project.loginApi.DTOs.AuthenticationDTO;
import com.project.loginApi.DTOs.UsuarioDTO;
import com.project.loginApi.entities.Ovino;
import com.project.loginApi.entities.Usuario.Usuario;
import com.project.loginApi.infra.security.TokenService;
import com.project.loginApi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContaService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity cadastro(UsuarioDTO usuarioDTO){
        try{
            if(this.usuarioRepository.findByLogin(usuarioDTO.login()) != null){
                return ResponseEntity.badRequest().build();
            }
            List<Ovino> listaOvino = new ArrayList<>();
            String senhaCodificada = new BCryptPasswordEncoder().encode(usuarioDTO.senha().toString());

            Usuario usuario = new Usuario(usuarioDTO.nome(), usuarioDTO.cpf(), usuarioDTO.login(), senhaCodificada,
                    usuarioDTO.telefone(), usuarioDTO.email(), usuarioDTO.isAtivo(),listaOvino);

            usuarioRepository.saveAndFlush(usuario);
            return ResponseEntity.ok().body(usuarioDTO);

        }catch (Exception e){
            e.printStackTrace();
        }
        //retorna uma exeception especifica
        return null;
    }
    public ResponseEntity login(AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new AuthenticationDTO.LoginResponseDTO(token));
    }
}
