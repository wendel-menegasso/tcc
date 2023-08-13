package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.repository.rendas.RendasRepository;
import br.com.mba.engenharia.de.software.service.rendas.RendasManager;
import br.com.mba.engenharia.de.software.service.rendas.RendasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RendasController{
    private static final Logger logger = LoggerFactory.getLogger(Renda.class);

    @Autowired
    RendasRepository repository;

    RendasService rendasService;

    @Bean
    public RendasService rendasService(){
        RendasManager rendasManager = new RendasManager(repository);
        return rendasManager;
    }

    public RendasController() {
        this.rendasService = rendasService();
    }

    @PostMapping("/criarRenda")
    public ResponseEntity<?> salvar(@RequestBody Renda renda){
        rendasService.setRendasRepository(repository);
        renda.setId(rendasService.count());
        rendasService.save(renda);
        logger.info(String.format("Renda cadastrada corretamente"));
        return ResponseEntity.ok(renda);
    }

    @GetMapping("/listarRenda")
    public ResponseEntity<?> listarConta(){
        rendasService.setRendasRepository(repository);
        return ResponseEntity.ok(rendasService.findAll());
    }

    @DeleteMapping("/deletarRenda/{id}")
    public ResponseEntity<?> deletarConta(@PathVariable("id") String id){
        rendasService.setRendasRepository(repository);
        Renda renda = new Renda();
        renda.setId(Integer.parseInt(id));
        if (repository.delete(renda.getId()) == 1){
            logger.info(String.format("Renda deletada com sucesso"));
            return ResponseEntity.ok(renda);
        }
        else{
            logger.info(String.format("Falha na exclusão"));
            return null;
        }
    }
    @PostMapping("/recebeDadosAlterarRenda")
    public ResponseEntity<?> recebeDadosAlterarConta(@RequestBody Renda renda){
        rendasService.setRendasRepository(repository);
        return ResponseEntity.ok(rendasService.findById(renda.getId()));
    }

    @PutMapping("alterarRenda")
    public ResponseEntity<?> alterarConta(@RequestBody Renda renda){
        rendasService.setRendasRepository(repository);
        if (repository.updateRendas(renda.getNome(), renda.getTipo(), renda.getValor(),
                renda.getData(), renda.getId()) == 1){
            return ResponseEntity.ok(renda);
        }
        else{
            return null;
        }
    }

}
