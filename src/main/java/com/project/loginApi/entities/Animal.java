package com.project.loginApi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.loginApi.entities.Usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Date;
import java.util.List;

@Entity(name = "tb_animal")
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "numRegistro")
    private Long numRegistro;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dataNascimento")
    private Date dataNascimento;

    @ManyToOne
    @JoinColumn(name = "id_pai")
    @JsonBackReference("animal-pai")
    private Animal pai;

    @ManyToOne
    @JoinColumn(name = "id_mae")
    @JsonBackReference("animal-mae")
    private Animal mae;

    @Column(name = "sexo")
    private char sexo;

    @OneToMany
    @JoinColumn(name = "id_vacina")
    @JsonManagedReference
    private List<Vacina> vacinas;

    @OneToMany
    @JoinColumn(name = "id_peso")
    @JsonManagedReference
    private List<Peso> pesos;

    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    @JsonBackReference("proprietario")
    private Usuario proprietario;

    public Animal(){

    }

    public Animal(Long numRegistro, String nome, Date dataNascimento, char sexo, List<Peso> pesos,List<Vacina> vacinas,
                  Usuario proprietario) {
        this.numRegistro = numRegistro;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.pesos = pesos;
        this.vacinas = vacinas;
        this.proprietario = proprietario;
        this.pai = new Animal();
        this.mae = new Animal();
    }

    public Animal(Long numRegistro, String nome, Date dataNascimento, Animal pai, Animal mae, char sexo, List<Peso> pesos,List<Vacina> vacinas,
                  Usuario proprietario) {
        this.numRegistro = numRegistro;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.pai = pai;
        this.mae = mae;
        this.sexo = sexo;
        this.pesos = pesos;
        this.vacinas = vacinas;
        this.proprietario = proprietario;
    }

    public Long getId() {
        return id;
    }

    public Long getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(Long numRegistro) {
        this.numRegistro = numRegistro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Animal getPai() {
        return pai;
    }

    public void setPai(Animal pai) {
        this.pai = pai;
    }

    public Animal getMae() {
        return mae;
    }

    public void setMae(Animal mae) {
        this.mae = mae;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public List<Peso> getPesos() {
        return this.pesos;
    }

    public void setPesos(List<Peso> pesos) {
        this.pesos = pesos;
    }

    public List<Vacina> getVacinas() {
        return vacinas;
    }

    public void setVacinas(List<Vacina> vacinas) {
        this.vacinas = vacinas;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }
}
