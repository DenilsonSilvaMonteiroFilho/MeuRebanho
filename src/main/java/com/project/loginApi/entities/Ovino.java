package com.project.loginApi.entities;

import jakarta.persistence.Entity;

import java.util.Date;
import java.util.List;

@Entity(name = "ovelha")
public class Ovino extends Animal {
    public Ovino(){
        super();
    }

    public Ovino(Long numRegistro, Date dataNascimento/*, Animal pai, Animal mae*/, char sexo, Double pesoNascimento, Usuario proprietario) {
        super(numRegistro, dataNascimento/*, pai, mae*/, sexo, pesoNascimento, proprietario);
    }
}
