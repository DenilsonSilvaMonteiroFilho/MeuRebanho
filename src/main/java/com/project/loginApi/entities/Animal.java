package com.project.loginApi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Date;
import java.util.List;

@Entity(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "numRegistro")
    private Long numRegistro;

    @Column(name = "dataNascimento")
    private Date dataNascimento;

    /*@OneToOne
    @JoinColumn(name = "pai_id")
    @JsonManagedReference
    private Animal pai;

    @OneToOne
    @JoinColumn(name = "mae_id")
    @JsonManagedReference
    private Animal mae;*/

    @Column(name = "sexo")
    private char sexo;

    @OneToMany
    @JoinColumn(name = "id_vacina")
    @JsonManagedReference
    private List<Vacina> vacinas;

    @Column(name = "pesoNascimento")
    private Double pesoNascimento;

    @ManyToOne
    @JoinColumn(name = "proprietario")
    @JsonBackReference
    private Usuario proprietario;

    public Animal(){

    }

    public Animal(Long numRegistro, Date dataNascimento/*, Animal pai, Animal mae*/, char sexo, Double pesoNascimento, Usuario proprietario) {
        this.numRegistro = numRegistro;
        this.dataNascimento = dataNascimento;
        //this.pai = pai;
        //this.mae = mae;
        this.sexo = sexo;
        this.pesoNascimento = pesoNascimento;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /*public Animal getPai() {
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
    }*/

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Double getPesoNascimento() {
        return pesoNascimento;
    }

    public void setPesoNascimento(Double pesoNascimento) {
        this.pesoNascimento = pesoNascimento;
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
