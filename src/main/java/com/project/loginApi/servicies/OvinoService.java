package com.project.loginApi.servicies;

import com.project.loginApi.entities.Animal;
import com.project.loginApi.entities.Ovino;
import com.project.loginApi.entities.Vacina;
import com.project.loginApi.repositories.OvinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OvinoService {
    @Autowired
    private OvinoRepository ovinoRepository;
    @Autowired
    private  VacinaService vacinaService;

    public Ovino save(Ovino ovino) {
        try {
            return ovinoRepository.save(ovino);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
                    ovelha.setPesoNascimento(newOvino.getPesoNascimento());
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

}

