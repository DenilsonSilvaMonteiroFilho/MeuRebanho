package com.project.loginApi.controllers;

import com.project.loginApi.entities.Ovino;
import com.project.loginApi.entities.Usuario;
import com.project.loginApi.servicies.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public List<Usuario> all(){
        return usuarioService.findAll();
    }

    @PostMapping()
    public Usuario novoUsuario(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @PostMapping("/list")
    public List<Usuario> novosUsuarios(@RequestBody List<Usuario> usuarios){
        return usuarioService.saveList(usuarios);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@RequestBody Usuario newUsuario, @PathVariable Long id) {
        return usuarioService.update(newUsuario, id);
    }

    @PutMapping("/addOvelha/{id}")
    public List<Ovino> addOvelhaParaUsuario(@RequestBody Ovino newOvino, @PathVariable Long id){
        return usuarioService.addOvino(newOvino, id);
    }

    @GetMapping("/{id}")
    public Optional<Usuario> findById(@PathVariable Long id){
        return usuarioService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        usuarioService.deleteById(id);
    }

}
