package com.project.loginApi.entities.Usuario;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.loginApi.entities.Ovino;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;
    private PapelUsuario papelUsuario;

    @OneToMany//(mappedBy = "usuario",cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ovino")
    @JsonManagedReference
    private List<Ovino> ovinoList;

    public Usuario(){

    }
    public Usuario(String nome, String cpf, String login, String senha, String telefone, String email, List<Ovino> ovinoList) {
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
        this.ovinoList = ovinoList;
    }

    public Usuario(String login, String encryptedPassword, PapelUsuario papel) {
        this.login = login;
        this.senha = encryptedPassword;
        this.papelUsuario = papel;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ovino> getOvinoList() {
        return ovinoList;
    }

    public void setOvelhaList(List<Ovino> ovinoList) {
        this.ovinoList = ovinoList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.papelUsuario == PapelUsuario.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else  return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
