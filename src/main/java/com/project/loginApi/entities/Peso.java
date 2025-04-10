package com.project.loginApi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity(name = "peso")
public class Peso {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "peso")
    private double peso;

    @Column(name = "dataRegistro")
    private LocalDate dataRegistro;

    @ManyToOne
    @JoinColumn(name = "id_animal")
    @JsonBackReference
    private Animal animal;

    public Peso(double peso, LocalDate dataRegistro) {
        this.peso = peso;
        this.dataRegistro = dataRegistro;
    }

    public Peso(double peso){
        this.peso = peso;
        this.dataRegistro = LocalDate.now();
    }

    public Peso(){

    }

    public Long getId() {
        return id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
