package com.project.loginApi.Mapper;

import com.project.loginApi.DTOs.AnimalCadastroDTO;
import com.project.loginApi.DTOs.AnimalSaidaDTO;
import com.project.loginApi.entities.Animal;
import com.project.loginApi.entities.Ovino;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnimalMapper {

    Ovino toEntityOvino(AnimalCadastroDTO dto);

    @Mapping(source = "pai.nome", target = "nomePai")
    @Mapping(source = "pai.numRegistro", target = "numRegistroPai")
    @Mapping(source = "mae.nome", target = "nomeMae")
    @Mapping(source = "mae.numRegistro", target = "numRegistroMae")
    AnimalSaidaDTO toSaidaDTOOvino(Ovino entity);
}
