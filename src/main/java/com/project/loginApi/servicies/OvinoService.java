package com.project.loginApi.servicies;

import com.project.loginApi.DTOs.AnimalCadastroDTO;
import com.project.loginApi.DTOs.AnimalSaidaDTO;
import com.project.loginApi.DTOs.OvinoDTO;
import com.project.loginApi.exceptions.DadoInvalidoException;
import com.project.loginApi.exceptions.NotFoundException;
import com.project.loginApi.exceptions.SalvarEntidadeException;
import com.project.loginApi.mapper.AnimalMapper;
import com.project.loginApi.entities.Ovino;
import com.project.loginApi.entities.Peso;
import com.project.loginApi.entities.Usuario.Usuario;
import com.project.loginApi.entities.Vacina;
import com.project.loginApi.repositories.OvinoRepository;
import com.project.loginApi.repositories.PesoRepository;
import com.project.loginApi.repositories.UsuarioRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OvinoService {
    @Autowired
    private OvinoRepository ovinoRepository;
    @Autowired
    private PesoRepository pesoRepository;
    @Autowired
    private  VacinaService vacinaService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AnimalMapper animalMapper;

    public Ovino save(Ovino ovino) {
        try {
            return ovinoRepository.save(ovino);
        } catch (Exception e) {
            throw new SalvarEntidadeException("Erro ao salvar o ovino: " + e.getMessage());
        }
    }

    public ResponseEntity<AnimalSaidaDTO> addOvino(AnimalCadastroDTO newOvino, Long idUsuario) throws BadRequestException {
        if (newOvino == null) {
            throw new BadRequestException("Dados do ovino não podem ser nulos.");
        }
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NotFoundException("Usuário com ID " + idUsuario + " nao encontrado."));

        try {
            if (usuario != null && newOvino != null) {
                Ovino ovino = ((Ovino) animalMapper.toEntityOvino(newOvino));

                registrarPaieMae(ovino, newOvino.idPai(), newOvino.idMae());

                save(ovino);

                usuario.getOvinoList().add(ovino);
                usuarioRepository.save(usuario);

                return ResponseEntity.status(HttpStatus.CREATED).body(animalMapper.toSaidaDTOOvino(ovino));
            }
        }catch (Exception e){
           throw new SalvarEntidadeException("Erro ao tentar adicionar ovino ao usuario " + e.getMessage());
        }
        throw new IllegalStateException("Erro inesperado ao adicionar ovino.");
    }

    public  ResponseEntity<AnimalSaidaDTO> addNovoPeso(double vlPeso, Long idOvino) {
        if (vlPeso <= 0) {
            throw new DadoInvalidoException("Valor peso cadastrado invalido. " + vlPeso);
        }
        Peso peso = new Peso(vlPeso);
        pesoRepository.save(peso);

        Ovino ovino = ovinoRepository.findById(idOvino)
                .orElseThrow(() -> new NotFoundException("Ovino com ID " + idOvino + "nao encontrado"));

        ovino.getPesos().add(peso);
        save(ovino);
        return ResponseEntity.status(HttpStatus.CREATED).body(animalMapper.toSaidaDTOOvino(ovino));
    }

    public List<Ovino> findAll() {
        return ovinoRepository.findAll();
    }

    public Optional<Ovino> findById(Long id) {
        return ovinoRepository.findById(id);
    }

    public void deleteById(Long id) {
        ovinoRepository.deleteById(id);
    }

    public List<Ovino> saveList(List<Ovino> listOvino) {
        try {
            for (Ovino ovino : listOvino) {
                ovinoRepository.save(ovino);
            }
            return listOvino;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Atualizar esse metodo
    public Ovino update(Ovino newOvino, Long id) {
        return (Ovino) ovinoRepository.findById(id)
                .map(ovelha -> {
                    ovelha.setNumRegistro(newOvino.getNumRegistro());
                    ovelha.setDataNascimento(newOvino.getDataNascimento());
                    //ovelha.setPai(newOvino.getPai());
                    //ovelha.setMae(newOvino.getMae());
                    ovelha.setSexo(newOvino.getSexo());
                    ovelha.setPesos(newOvino.getPesos());
                    ovelha.setVacinas(newOvino.getVacinas());
                    return ovinoRepository.save(ovelha);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    public ResponseEntity<Ovino> cadastraVacinaParaOvino(Vacina vacina, Long idOvino) throws BadRequestException {
        if (vacina == null){
            throw new BadRequestException("Dados da vacina não podem ser nulos.");
        }
        vacinaService.save(vacina);
        Ovino ovino = ovinoRepository.findById(idOvino)
                .orElseThrow(() -> new NotFoundException("Ovino com id " + idOvino + " nao encontrado."));

        ovino.getVacinas().add(vacina);
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(save(ovino));
        }catch (Exception e){
            throw new SalvarEntidadeException("Erro ao tentar salvar entidade." + e.getMessage());
        }
    }

    public Ovino registrarPaieMae(Ovino ovino,Long idPai, Long idMae){
        if(idPai != null && idPai > 0){
            Ovino ovinoPai = ovinoRepository.findById(idPai)
                    .orElseThrow(() -> new NotFoundException("Ovino com id " + idPai + " nao encontrado."));

            ovino.setPai(ovinoPai);
        }

        if(idMae != null && idMae != 0){
            Ovino ovinoMae = ovinoRepository.findById(idMae)
                    .orElseThrow(() -> new NotFoundException("Ovino com id " + idMae + " nao encontrado."));

            ovino.setMae(ovinoMae);
        }

        try{
            return ovinoRepository.save(ovino);
        }catch (Exception e){
            throw new SalvarEntidadeException("Erro ao salvar alteracao na entidade ovino " + e.getMessage());
        }
    }

}

