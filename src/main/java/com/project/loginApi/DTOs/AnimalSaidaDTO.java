package com.project.loginApi.DTOs;

import com.project.loginApi.entities.Peso;
import com.project.loginApi.entities.Vacina;

import java.util.Date;
import java.util.List;

public record AnimalSaidaDTO(
        String nome,
        Long numRegistro,
        Date dataNascimento,
        char sexo,
        List<Peso> pesos,
        List<Vacina> vacinas,
        Long numRegistroPai,
        String nomePai,
        Long numRegistroMae,
        String nomeMae
        ) {
}
