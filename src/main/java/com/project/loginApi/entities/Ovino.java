package com.project.loginApi.entities;

import com.project.loginApi.entities.Usuario.Usuario;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity(name = "ovelha")
public class Ovino extends Animal {
    public Ovino(){
        super();
    }

    public Ovino(Long numRegistro, Date dataNascimento/*, Animal pai, Animal mae*/, char sexo, Double pesoNascimento, Usuario proprietario) {
        super(numRegistro, dataNascimento/*, pai, mae*/, sexo, pesoNascimento, proprietario);
    }
}
