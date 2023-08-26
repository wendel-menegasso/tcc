package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.dto.RendasDTO;
import br.com.mba.engenharia.de.software.dto.RendasRetornoDTO;
import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.repository.rendas.RendasRepository;
import br.com.mba.engenharia.de.software.service.rendas.RendasManager;
import br.com.mba.engenharia.de.software.service.rendas.RendasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<?> salvar(@RequestBody RendasDTO rendasDTO){
        Renda renda = rendasDTO.parseRendasDTOToRenda();
        rendasService.setRendasRepository(repository);
        renda.setId(rendasService.count());
        Renda rendaRetorno = rendasService.save(renda);
        RendasRetornoDTO rendasRetornoDTO = rendaRetorno.parseRendaToRendasRetornoDTO();

        if (rendasRetornoDTO != null){
            logger.info(String.format("Renda cadastrada corretamente"));
            return new ResponseEntity<>(rendasRetornoDTO, HttpStatus.CREATED);
        }
        else{
            logger.info(String.format("Renda não cadastrada"));
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listarRenda")
    public ResponseEntity<?> listarConta(){
        rendasService.setRendasRepository(repository);
        List<RendasRetornoDTO> rendasRetornoDTOList = new ArrayList<>();
        for (Renda renda : rendasService.findAll()){
            rendasRetornoDTOList.add(renda.parseRendaToRendasRetornoDTO());
        }
        return new ResponseEntity<>(rendasRetornoDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/deletarRenda/{id}")
    public ResponseEntity<?> deletarConta(@PathVariable("id") String id){
        rendasService.setRendasRepository(repository);
        Renda renda = new Renda();
        renda.setId(Integer.parseInt(id));
        List<RendasRetornoDTO> rendasRetornoDTOList = new ArrayList<>();
        for (Renda rendaRetorno : repository.delete(renda.getId())){
            rendasRetornoDTOList.add(rendaRetorno.parseRendaToRendasRetornoDTO());
        }
        if (rendasRetornoDTOList.size() > 0){
            logger.info(String.format("Renda deletada com sucesso"));
            return new ResponseEntity<>(rendasRetornoDTOList, HttpStatus.OK);
        }
        else{
            logger.info(String.format("Falha na exclusão"));
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping("/recebeDadosAlterarRenda")
    public ResponseEntity<?> recebeDadosAlterarConta(@RequestBody RendasDTO rendasDTO){
        Renda renda = rendasDTO.parseRendasDTOToRenda();
        rendasService.setRendasRepository(repository);
        Renda rendaRetorno  = rendasService.findById(renda.getId());
        RendasRetornoDTO rendasRetornoDTO = rendaRetorno.parseRendaToRendasRetornoDTO();
        if (rendasRetornoDTO != null){
            return new ResponseEntity<>(rendasRetornoDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("alterarRenda")
    public ResponseEntity<?> alterarConta(@RequestBody RendasDTO rendasDTO){
        Renda renda = rendasDTO.parseRendasDTOToRenda();
        rendasService.setRendasRepository(repository);
        List<RendasRetornoDTO> rendasRetornoDTOList = new ArrayList<>();
        for (Renda rendaRetorno : repository.updateRendas(renda.getNome(), renda.getTipo(), renda.getValor(),
                renda.getData(), renda.getId())){
            rendasRetornoDTOList.add(rendaRetorno.parseRendaToRendasRetornoDTO());
        }
        if (rendasRetornoDTOList.size() > 0){
            return new ResponseEntity<>(rendasRetornoDTOList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

}
