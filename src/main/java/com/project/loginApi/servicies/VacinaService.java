package com.project.loginApi.servicies;

import com.project.loginApi.entities.Vacina;
import com.project.loginApi.repositories.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacinaService {
    @Autowired
    private VacinaRepository vacinaRepository;

    public Vacina save(Vacina vacina) {
        try {
            return vacinaRepository.save(vacina);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Vacina> findAll() {
        return vacinaRepository.findAll();
    }

    public Optional<Vacina> findById(Long id) {
        return vacinaRepository.findById(id);
    }

    public void deleteById(Long id) {
        vacinaRepository.deleteById(id);
    }

    public List<Vacina> saveList(List<Vacina> listVacina) {
        try {
            for (Vacina vacina : listVacina) {
                vacinaRepository.save(vacina);
            }
            return listVacina;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vacina update(Vacina newVacina, Long id) {
        return vacinaRepository.findById(id)
                .map(vacina -> {
                    vacina.setNome(newVacina.getNome());
                    vacina.setQuantidadeMinistrada(newVacina.getQuantidadeMinistrada());
                    vacina.setFormaMinistrda(newVacina.getFormaMinistrda());
                    vacina.setDataProximaDose(newVacina.getDataProximaDose());
                    vacina.setIsDoseUnica(newVacina.getIsDoseUnica());
                    return vacinaRepository.save(vacina);
                })
                .orElseGet(() -> {
                    return null;
                });
    }
}
