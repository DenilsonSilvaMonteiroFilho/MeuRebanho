package com.project.loginApi.entities;

import com.project.loginApi.entities.Usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;

import java.util.Date;
import java.util.List;

@Entity(name = "ovelha")
public class Ovino extends Animal {
    public Ovino(){
        super();
    }
    public Ovino(Long numRegistro, String nome, Date dataNascimento, char sexo, List<Peso> pesos, List<Vacina> vacinas, Usuario proprietario) {
        super(numRegistro, nome, dataNascimento, sexo, pesos, vacinas, proprietario);
    }
    public Ovino(Long numRegistro, String nome, Date dataNascimento, Ovino pai, Ovino mae, char sexo, List<Peso> pesos, List<Vacina> vacinas, Usuario proprietario) {
        super(numRegistro, nome, dataNascimento, pai, mae, sexo, pesos, vacinas, proprietario);
    }
}
