package br.com.mba.engenharia.de.software.refactoring.controller;

import br.com.mba.engenharia.de.software.refactoring.dto.origens.OrigensDTO;
import br.com.mba.engenharia.de.software.refactoring.entity.contas.BancoDescr;
import br.com.mba.engenharia.de.software.refactoring.entity.contas.Conta;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import br.com.mba.engenharia.de.software.repository.gastos.GastosRepository;
import br.com.mba.engenharia.de.software.repository.rendas.RendasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class OrigensController {

    private static final Logger logger = LoggerFactory.getLogger(OrigensController.class);

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private RendasRepository rendasRepository;

    @Autowired
    private GastosRepository gastosRepository;

    @PostMapping("/carregarOrigem")
    public ResponseEntity<?> listarOrigens(@RequestBody String user){
        logger.info(String.format("Entrou no método carregarOrigem"));
        Integer usuario = Integer.parseInt(user);
        List<OrigensDTO> origensDTOList =contaRepository.findAll(usuario)
                .stream()
                .map(conta->     {
                    OrigensDTO origensDTO = new OrigensDTO();
                    BancoDescr bancoDescr = new BancoDescr();
                    origensDTO.setOrigem(conta.getId() + " - " + bancoDescr.getBanco(conta.getBanco()) + " - " + conta.getAgencia() + " - " + conta.getConta());
                    origensDTO.setSaldo(conta.getSaldo());
                    origensDTO.setId(conta.getId());
                    return origensDTO;
                })
                .collect(Collectors.toList());
                    return ResponseEntity.ok(origensDTOList.isEmpty() ? null : origensDTOList);
    }
}
