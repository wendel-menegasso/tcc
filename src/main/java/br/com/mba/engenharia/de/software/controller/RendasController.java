package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.dto.*;
import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
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

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RendasController{
    private static final Logger logger = LoggerFactory.getLogger(Renda.class);

    @Autowired
    RendasRepository repository;

    @Autowired
    ContaRepository contaRepository;

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
        rendasService.setRendasRepository(repository);
        Renda rendas = rendasDTO.parseRendasDTOToRenda();

        rendas.setId(rendasService.count());
        Renda rendasRetorno = rendasService.save(rendas);
        RendasRetornoDTO rendasRespostaDTO = new RendasRetornoDTO(rendasRetorno);

        if (rendasRespostaDTO != null){
            ContaController controller = new ContaController(contaRepository);
            ContaDTOAlterar contaDTOAlterar = new ContaDTOAlterar(String.valueOf(rendasRetorno.getOrigem()), rendasRetorno.getUsuario());
            ContaDTORetorno contaSelecionada = controller.pegaConta(contaDTOAlterar);

            Double valorRenda = Double.parseDouble(rendasRetorno.getValor());
            Double valorConta = Double.parseDouble(contaSelecionada.getSaldo());

            Double valorAtualizado = valorConta + valorRenda;

            contaSelecionada.setSaldo(String.valueOf(valorAtualizado));

            ContaDTOAlterarFull contaDTOAlterarFull = new ContaDTOAlterarFull(contaSelecionada);
            ContaDTORetorno contaAlterada = controller.atualizaConta(contaDTOAlterarFull);

            if (contaAlterada != null) {
                logger.info(String.format("Despesa cadastrada corretamente"));
                return ResponseEntity.ok(rendasRespostaDTO);
            } else {
                logger.info(String.format("Despesa cadastrada incorretamente"));
                return ResponseEntity.ok(null);
            }
        }
        logger.info(String.format("Despesa cadastrada incorretamente"));
        return ResponseEntity.ok(null);

    }

    @PostMapping("/listarRenda")
    public ResponseEntity<?> listarRenda(@RequestBody String req){
        rendasService.setRendasRepository(repository);
        List<Renda> rendaList = rendasService.findAll(Integer.parseInt(req));
        return ResponseEntity.ok(rendaList);
    }

    @DeleteMapping("/deletarRenda/{id}")
    public ResponseEntity<?> deletarRenda(@PathVariable("id") String id){
        rendasService.setRendasRepository(repository);
        Renda renda = new Renda();
        renda.setId(Integer.parseInt(id));
        Renda rendaRetorno = rendasService.findById(renda.getId());
        RendasRetornoDTO rendasRetornoDTO = rendaRetorno.parseRendaToRendasRetornoDTO();
        if (rendasService.delete(renda.getId()) > 0) {
            ContaController controller = new ContaController(contaRepository);
            ContaDTOAlterar contaDTOAlterar = new ContaDTOAlterar(String.valueOf(rendaRetorno.getOrigem()), rendaRetorno.getUsuario());
            ContaDTORetorno contaSelecionada = controller.pegaConta(contaDTOAlterar);

            Double valorRenda = Double.parseDouble(rendaRetorno.getValor());
            Double valorConta = Double.parseDouble(contaSelecionada.getSaldo());

            Double valorAtualizado = valorConta - valorRenda;

            contaSelecionada.setSaldo(String.valueOf(valorAtualizado));

            ContaDTOAlterarFull contaDTOAlterarFull = new ContaDTOAlterarFull(contaSelecionada);
            ContaDTORetorno contaAlterada = controller.atualizaConta(contaDTOAlterarFull);

            if (contaAlterada != null) {
                logger.info(String.format("Renda deletada com sucesso"));
                return ResponseEntity.ok(rendasRetornoDTO);
            } else {
                logger.info(String.format("Falha na exclusão"));
                return ResponseEntity.ok(null);
            }
        }
        logger.info(String.format("Falha na exclusão"));
        return ResponseEntity.ok(null);
    }
    @PostMapping("/recebeDadosAlterarRenda")
    public ResponseEntity<?> recebeDadosAlterarRenda(@RequestBody RendasDTOFull rendasDTOFull){
        Renda renda = rendasDTOFull.parseRendasDTOToRenda();
        rendasService.setRendasRepository(repository);
        Renda rendaRetorno  = rendasService.findById(renda.getId());
        RendasRetornoDTO rendasRetornoDTO = rendaRetorno.parseRendaToRendasRetornoDTO();
        if (rendasRetornoDTO != null){
            return ResponseEntity.ok(rendasRetornoDTO);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("alterarRenda")
    public ResponseEntity<?> alterarRenda(@RequestBody RendasAlterarDTO rendasAlterarDTO){
        Renda renda = rendasAlterarDTO.parseRendasDTOToRenda();
        rendasService.setRendasRepository(repository);
        Renda rendaRetorno = rendasService.findById(renda.getId());

        Double rendaAtualizada = Double.parseDouble(rendaRetorno.getValor()) - Double.parseDouble(renda.getValor());

        RendasRetornoDTO rendasRetornoDTO = rendaRetorno.parseRendaToRendasRetornoDTO();
        Integer retorno = repository.updateRendas(renda.getNome(), renda.getTipo(), renda.getValor(),
                renda.getData(), renda.getId(), renda.getUsuario());
        if (retorno > 0){
            ContaController controller = new ContaController(contaRepository);
            ContaDTOAlterar contaDTOAlterar = new ContaDTOAlterar(String.valueOf(rendaRetorno.getOrigem()), rendaRetorno.getUsuario());
            ContaDTORetorno contaSelecionada = controller.pegaConta(contaDTOAlterar);

            Double valorConta = Double.parseDouble(contaSelecionada.getSaldo());

            Double valorAtualizado = valorConta - rendaAtualizada;

            contaSelecionada.setSaldo(String.valueOf(valorAtualizado));

            ContaDTOAlterarFull contaDTOAlterarFull = new ContaDTOAlterarFull(contaSelecionada);
            ContaDTORetorno contaAlterada = controller.atualizaConta(contaDTOAlterarFull);
            logger.info(String.format("Renda alterada com sucesso!"));
            return ResponseEntity.ok(rendasRetornoDTO);
        }
        else{
            logger.info(String.format("Não foi possivel alterar a renda"));
            return ResponseEntity.ok(null);
        }
    }

}
