package com.project.loginApi.controllers;

import com.project.loginApi.entities.Ovino;
import com.project.loginApi.entities.Vacina;
import com.project.loginApi.servicies.OvinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/ovino")//Queria colocar api/id do usuario/ovino
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OvinoController {

    @Autowired
    private OvinoService ovinoService;

    @GetMapping()
    public List<Ovino> all(){
        return ovinoService.findAll();
    }

    @PostMapping()
    public Ovino novoOvino(@RequestBody Ovino ovino){
        return ovinoService.save(ovino);
    }

    @PostMapping("/list")
    public List<Ovino> novasOvinos(@RequestBody List<Ovino> ovinos){
        return ovinoService.saveList(ovinos);
    }

    @PutMapping("/{id}")
    public Ovino atualizarOvelha(@RequestBody Ovino newOvino, @PathVariable Long id) {
        return ovinoService.update(newOvino, id);
    }

    @PutMapping("/cadastroVacina/{id}")
    public Ovino cadastroVacina(@RequestBody Vacina vacina, @PathVariable Long id){
        return ovinoService.cadastraVacinaParaOvino(vacina, id);
    }

    @GetMapping("/{id}")
    public Optional<Ovino> findById(@PathVariable Long id){
        return ovinoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        ovinoService.deleteById(id);
    }

}
