package com.project.loginApi.DTOs;

import com.project.loginApi.entities.Peso;
import com.project.loginApi.entities.Usuario.Usuario;
import com.project.loginApi.entities.Vacina;

import java.util.Date;
import java.util.List;

public record OvinoDTO(Long numRegstro, Date dataNascimento, char sexo,List<Peso> pesos,
                        List<Vacina> vacinas) {
}
