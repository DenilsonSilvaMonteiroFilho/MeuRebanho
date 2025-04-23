package com.project.loginApi.servicies;

import com.project.loginApi.DTOs.AnimalCadastroDTO;
import com.project.loginApi.DTOs.AnimalSaidaDTO;
import com.project.loginApi.DTOs.OvinoDTO;
import com.project.loginApi.Mapper.AnimalMapper;
import com.project.loginApi.entities.Ovino;
import com.project.loginApi.entities.Peso;
import com.project.loginApi.entities.Usuario.Usuario;
import com.project.loginApi.entities.Vacina;
import com.project.loginApi.repositories.OvinoRepository;
import com.project.loginApi.repositories.PesoRepository;
import com.project.loginApi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public OvinoDTO save(Ovino ovino) {
        OvinoDTO ovinoDTO = new OvinoDTO(ovino.getNumRegistro(),ovino.getDataNascimento(),
                ovino.getSexo(),ovino.getPesos(),ovino.getVacinas());
        try {
            ovinoRepository.save(ovino);
            return ovinoDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseEntity<AnimalSaidaDTO> addOvino(AnimalCadastroDTO newOvino, Long idUsuario){
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        if (usuario!=null && newOvino !=null){
            Ovino ovino = ((Ovino) animalMapper.toEntityOvino(newOvino));
            registrarPaieMae(ovino, newOvino.idPai(), newOvino.idMae());
            ovinoRepository.save(ovino);
            usuario.getOvinoList().add(ovino);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(animalMapper.toSaidaDTOOvino(ovino));
        }
        //Adicionar tratamento de exception
        return null;
    }

    public  OvinoDTO addNovoPeso(double vlPeso, Long idOvino){
        Peso peso = new Peso(vlPeso);
        pesoRepository.save(peso);

        return (OvinoDTO) ovinoRepository.findById(idOvino)
                .map(ovino -> {
                    ovino.getPesos().add(peso);
                    ovinoRepository.save(ovino);

                    OvinoDTO ovinoDTO = new OvinoDTO(ovino.getNumRegistro(),ovino.getDataNascimento(),
                            ovino.getSexo(),ovino.getPesos(),ovino.getVacinas());
                    return ovinoDTO;

                })
                .orElseGet(() -> {
                    return null;
                });


        //return ovinoDTO;
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

    public Ovino cadastraVacinaParaOvino(Vacina vacina, Long idOvino){
        vacinaService.save(vacina);
        return (Ovino) ovinoRepository.findById(idOvino)
                .map(ovino -> {
                    ovino.getVacinas().add(vacina);
                    return ovinoRepository.save(ovino);
                })
                .orElseGet(() -> {
                    return null;
                });
    }
    public Ovino registrarPaieMae(Ovino ovino,Long idPai, Long idMae){
        if(idPai != null && idPai > 0){
            Ovino ovinoPai = ovinoRepository.findById(idPai).get();
            //Colocar uma tratamento para quando o id nao existir
            ovino.setPai(ovinoPai);
        }
        if(idMae != null && idMae != 0){
            Ovino ovinoMae = ovinoRepository.findById(idMae).get();
            ovino.setMae(ovinoMae);
        }
        return ovinoRepository.save(ovino);
    }

}

