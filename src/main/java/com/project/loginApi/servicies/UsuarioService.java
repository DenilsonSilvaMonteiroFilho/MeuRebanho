package com.project.loginApi.servicies;

import com.project.loginApi.entities.Ovino;
import com.project.loginApi.entities.Usuario;
import com.project.loginApi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OvinoService ovinoService;

    public Usuario save(Usuario usuario){
        try{
            return usuarioRepository.saveAndFlush(usuario);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }

    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> saveList(List <Usuario> listUsuario) {
        try{
            for (Usuario usuario: listUsuario) {
                usuarioRepository.saveAndFlush(usuario);
            }
            return listUsuario;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Usuario update(Usuario newUsuario, Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(newUsuario.getNome());
                    usuario.setCpf(newUsuario.getCpf());
                    usuario.setLogin(newUsuario.getLogin());
                    usuario.setSenha(newUsuario.getSenha());
                    usuario.setTelefone(newUsuario.getTelefone());
                    usuario.setEmail(newUsuario.getEmail());
                    return usuarioRepository.save(usuario);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    public List<Ovino> addOvino(Ovino newOvino, Long idUsuario){
        Usuario usuario = usuarioRepository.getById(idUsuario);
        if (usuario!=null&& newOvino !=null){
            ovinoService.save(newOvino);
            usuario.getOvinoList().add(newOvino);
            usuarioRepository.save(usuario);
            return usuario.getOvinoList();
        }
        //Adicionar tratamento de exception
        return null;
    }
}
