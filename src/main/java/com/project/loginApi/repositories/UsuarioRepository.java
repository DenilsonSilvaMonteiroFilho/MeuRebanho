package com.project.loginApi.repositories;

import com.project.loginApi.entities.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario>  findByLogin(String Login);
    Optional<Usuario> findByEmail(String email);
}
