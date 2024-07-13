package com.project.loginApi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity(name = "vacina")
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome")
    private String nome;

    //@Column(name = "tipo")
    //private Tipo tipo

    @Column(name = "quantidadeMinistrada")
    private String quantidadeMinistrada;

    @Column(name = "formaMinistrada")
    private String formaMinistrda;

    @Column(name = "dataProximaDose")
    private Date dataProximaDose;

    @Column(name = "isDoseUnica")
    private Boolean isDoseUnica;

    @ManyToOne
    @JoinColumn(name = "id_animal")
    @JsonBackReference
    private Animal animal;

    public Vacina(){
    }

    public Vacina(String nome, String quantidadeMinistrada, String formaMinistrda, Date dataProximaDose, Boolean isDoseUnica) {
        this.nome = nome;
        this.quantidadeMinistrada = quantidadeMinistrada;
        this.formaMinistrda = formaMinistrda;
        this.dataProximaDose = dataProximaDose;
        this.isDoseUnica = isDoseUnica;
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

    public String getQuantidadeMinistrada() {
        return quantidadeMinistrada;
    }

    public void setQuantidadeMinistrada(String quantidadeMinistrada) {
        this.quantidadeMinistrada = quantidadeMinistrada;
    }

    public String getFormaMinistrda() {
        return formaMinistrda;
    }

    public void setFormaMinistrda(String formaMinistrda) {
        this.formaMinistrda = formaMinistrda;
    }

    public Date getDataProximaDose() {
        return dataProximaDose;
    }

    public void setDataProximaDose(Date dataProximaDose) {
        this.dataProximaDose = dataProximaDose;
    }

    public Boolean getIsDoseUnica() {
        return isDoseUnica;
    }

    public void setIsDoseUnica(Boolean isDoseUnica) {
        this.isDoseUnica = isDoseUnica;
    }
}
