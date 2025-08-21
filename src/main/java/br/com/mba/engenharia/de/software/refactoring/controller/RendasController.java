package br.com.mba.engenharia.de.software.refactoring.controller;

import br.com.mba.engenharia.de.software.refactoring.dto.contas.ContaDTOAlterar;
import br.com.mba.engenharia.de.software.refactoring.dto.contas.ContaDTOAlterarFull;
import br.com.mba.engenharia.de.software.refactoring.dto.contas.ContaDTORetorno;
import br.com.mba.engenharia.de.software.refactoring.dto.rendas.*;
import br.com.mba.engenharia.de.software.refactoring.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.refactoring.service.GenericService;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import br.com.mba.engenharia.de.software.repository.rendas.RendasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RendasController {
    private static final Logger logger = LoggerFactory.getLogger(RendasController.class);

    @Autowired
    private RendasRepository repository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private GenericService genericService;

    @PostMapping("/criarRenda")
    public ResponseEntity<?> salvar(@RequestBody RendasDTO rendasDTO) {
        Renda rendas = rendasDTO.parseRendasDTOToRenda();

        rendas.setId(repository.count());
        Renda rendasRetorno = repository.save(rendas);
        RendasRetornoDTO rendasRespostaDTO = new RendasRetornoDTO(rendasRetorno);

        ContaDTOAlterar contaDTOAlterar = new ContaDTOAlterar(String.valueOf(rendasRetorno.getOrigem()), rendasRetorno.getUsuario());
        ContaDTORetorno contaSelecionada = genericService.pegaConta(contaDTOAlterar);

        Double valorRenda = Double.parseDouble(rendasRetorno.getValor());
        Double valorConta = Double.parseDouble(contaSelecionada.getSaldo());

        Double valorAtualizado = valorConta + valorRenda;

        contaSelecionada.setSaldo(String.valueOf(valorAtualizado));

        ContaDTOAlterarFull contaDTOAlterarFull = new ContaDTOAlterarFull(contaSelecionada);
        ContaDTORetorno contaAlterada = genericService.atualizaConta(contaDTOAlterarFull);

        if (contaAlterada != null) {
            logger.info(String.format("Despesa cadastrada corretamente"));
            return ResponseEntity.ok(rendasRespostaDTO);
        } else {
            logger.info(String.format("Despesa cadastrada incorretamente"));
            return ResponseEntity.ok(null);
        }

    }

    @PostMapping("/listarRenda")
    public ResponseEntity<List<Renda>> listarRenda(@RequestBody String req) {
        List<Renda> rendaList = repository.findAll(Integer.parseInt(req));
        return ResponseEntity.ok(rendaList);
    }

    @PostMapping("/gerarRelatorioRenda")
    public ResponseEntity<FileSystemResource> gerarRelatorioRenda(@RequestBody RendaDTOFull rendasDTOFull) throws IOException {
        String filename = "rendas.csv";
        InputStreamResource file = new InputStreamResource(genericService.load(filename));

        FileSystemResource fileSystemResource = new FileSystemResource(new File(filename));

        // Configura os cabeçalhos da resposta
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileSystemResource.getFilename());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileSystemResource.contentLength()));

        // Cria o ResponseEntity com o objeto FileSystemResource e os cabeçalhos
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(fileSystemResource);
    }

    @DeleteMapping("/deletarRenda/{id}")
    public ResponseEntity<?> deletarRenda(@PathVariable("id") String id) {
        Renda renda = new Renda();
        renda.setId(Integer.parseInt(id));
        Renda rendaRetorno = repository.findById(renda.getId());
        RendasRetornoDTO rendasRetornoDTO = rendaRetorno.parseRendaToRendasRetornoDTO();
        if (repository.delete(renda.getId()) > 0) {
            ContaDTOAlterar contaDTOAlterar = new ContaDTOAlterar(String.valueOf(rendaRetorno.getOrigem()), rendaRetorno.getUsuario());
            ContaDTORetorno contaSelecionada = genericService.pegaConta(contaDTOAlterar);

            Double valorRenda = Double.parseDouble(rendaRetorno.getValor());
            Double valorConta = Double.parseDouble(contaSelecionada.getSaldo());

            Double valorAtualizado = valorConta - valorRenda;

            contaSelecionada.setSaldo(String.valueOf(valorAtualizado));

            ContaDTOAlterarFull contaDTOAlterarFull = new ContaDTOAlterarFull(contaSelecionada);
            ContaDTORetorno contaAlterada = genericService.atualizaConta(contaDTOAlterarFull);

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
    public ResponseEntity<?> recebeDadosAlterarRenda(@RequestBody RendasDTOFull rendasDTOFull) {
        Renda renda = rendasDTOFull.parseRendasDTOToRenda();
        Renda rendaRetorno = repository.findById(renda.getId());
        RendasRetornoDTO rendasRetornoDTO = rendaRetorno.parseRendaToRendasRetornoDTO();
        if (rendasRetornoDTO != null) {
            return ResponseEntity.ok(rendasRetornoDTO);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("alterarRenda")
    public ResponseEntity<?> alterarRenda(@RequestBody RendasAlterarDTO rendasAlterarDTO) {
        Renda renda = rendasAlterarDTO.parseRendasDTOToRenda();
        Renda rendaRetorno = repository.findById(renda.getId());

        Double rendaAtualizada = Double.parseDouble(rendaRetorno.getValor()) - Double.parseDouble(renda.getValor());

        RendasRetornoDTO rendasRetornoDTO = rendaRetorno.parseRendaToRendasRetornoDTO();
        Integer retorno = repository.updateRendas(renda.getNome(), renda.getTipo(), renda.getValor(),
                renda.getData(), renda.getId(), renda.getUsuario());
        if (retorno > 0) {
            ContaDTOAlterar contaDTOAlterar = new ContaDTOAlterar(String.valueOf(rendaRetorno.getOrigem()), rendaRetorno.getUsuario());
            ContaDTORetorno contaSelecionada = genericService.pegaConta(contaDTOAlterar);

            Double valorConta = Double.parseDouble(contaSelecionada.getSaldo());

            Double valorAtualizado = valorConta - rendaAtualizada;

            contaSelecionada.setSaldo(String.valueOf(valorAtualizado));

            ContaDTOAlterarFull contaDTOAlterarFull = new ContaDTOAlterarFull(contaSelecionada);
            ContaDTORetorno contaAlterada = genericService.atualizaConta(contaDTOAlterarFull);
            logger.info(String.format("Renda alterada com sucesso!"));
            return ResponseEntity.ok(rendasRetornoDTO);
        } else {
            logger.info(String.format("Não foi possivel alterar a renda"));
            return ResponseEntity.ok(null);
        }
    }

}
