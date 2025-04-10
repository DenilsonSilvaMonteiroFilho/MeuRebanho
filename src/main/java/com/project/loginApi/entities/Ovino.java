package com.project.loginApi.entities;

import com.project.loginApi.entities.Usuario.Usuario;
import jakarta.persistence.Entity;

import java.util.Date;
import java.util.List;

@Entity(name = "ovelha")
public class Ovino extends Animal {
    public Ovino(){
        super();
    }

    public Ovino(Long numRegistro, Date dataNascimento/*, Animal pai, Animal mae*/, char sexo, List<Peso> pesos, List<Vacina> vacinas, Usuario proprietario) {
        super(numRegistro, dataNascimento/*, pai, mae*/, sexo, pesos, vacinas, proprietario);
    }
}
