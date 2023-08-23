package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.dto.GastosDTO;
import br.com.mba.engenharia.de.software.dto.GastosDTOFull;
import br.com.mba.engenharia.de.software.dto.GastosRespostaDTO;
import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.repository.gastos.GastosRepository;
import br.com.mba.engenharia.de.software.service.gastos.GastosManager;
import br.com.mba.engenharia.de.software.service.gastos.GastosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GastosController {

    private static final Logger logger = LoggerFactory.getLogger(Gastos.class);

    @Autowired
    GastosRepository repository;

    GastosService gastosService;

    @Bean
    public GastosService gastosService(){
        GastosManager gastosManager = new GastosManager(repository);
        return gastosManager;
    }

    public GastosController() {
        this.gastosService = gastosService();
    }

    @PostMapping("/criarGasto")
    public ResponseEntity<?> salvar(@RequestBody GastosDTO gastosDTO){
        gastosService.setGastosRepository(repository);
        Gastos gastos = gastosDTO.parseGastosToDTOGastos();

        gastos.setId(gastosService.count());
        Gastos gastosRetorno = gastosService.save(gastos);
        GastosRespostaDTO gastosRespostaDTO = new GastosRespostaDTO(gastosRetorno);

        if (gastosRespostaDTO != null){
            logger.info(String.format("Renda cadastrada corretamente"));
            return new ResponseEntity<>(gastosRetorno, HttpStatus.CREATED);
        }
        else{
            logger.info(String.format("Renda cadastrada incorretamente"));
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listarGasto")
    public ResponseEntity<?> listarGasto(){
        gastosService.setGastosRepository(repository);
        return ResponseEntity.ok(gastosService.findAll());
    }

    @DeleteMapping("/deletarGasto/{id}")
    public ResponseEntity<?> deletarGasto(@PathVariable("id") String id){
        gastosService.setGastosRepository(repository);
        Renda renda = new Renda();
        renda.setId(Integer.parseInt(id));
        if (repository.delete(renda.getId()) == 1){
            logger.info(String.format("Renda deletada com sucesso"));
            return new ResponseEntity<>(1, HttpStatus.OK);
        }
        else{
            logger.info(String.format("Falha na exclusão"));
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/recebeDadosAlterarGasto")
    public ResponseEntity<?> recebeDadosAlterarGasto(@RequestBody Gastos gastos){
        gastosService.setGastosRepository(repository);
        return ResponseEntity.ok(gastosService.findById(gastos.getId()));
    }

    @PutMapping("alterarGasto")
    public ResponseEntity<?> alterarGasto(@RequestBody GastosDTOFull gastosDTOFull){
        gastosService.setGastosRepository(repository);
        Gastos gastos = gastosDTOFull.parseGastosToDTOFullToGastos();
        int retorno = gastosService.updateGastos(gastos.getNome(), gastos.getTipo(), gastos.getValor(),
                gastos.getData(), gastos.getId());
        if (retorno == 1){
            GastosRespostaDTO gastosRespostaDTO = new GastosRespostaDTO(gastos);
            return new ResponseEntity<>(gastosRespostaDTO, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
