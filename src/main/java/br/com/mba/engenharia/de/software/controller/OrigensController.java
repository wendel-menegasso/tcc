package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.dto.OrigensDTO;
import br.com.mba.engenharia.de.software.entity.contas.BancoDescr;
import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import br.com.mba.engenharia.de.software.repository.gastos.GastosRepository;
import br.com.mba.engenharia.de.software.repository.rendas.RendasRepository;
import br.com.mba.engenharia.de.software.service.contas.ContaManager;
import br.com.mba.engenharia.de.software.service.contas.ContaService;
import br.com.mba.engenharia.de.software.service.gastos.GastosManager;
import br.com.mba.engenharia.de.software.service.gastos.GastosService;
import br.com.mba.engenharia.de.software.service.rendas.RendasManager;
import br.com.mba.engenharia.de.software.service.rendas.RendasService;
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

@RestController
@CrossOrigin(origins = "*")
public class OrigensController {

    private static final Logger logger = LoggerFactory.getLogger(Conta.class);

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    RendasRepository rendasRepository;

    @Autowired
    GastosRepository gastosRepository;

    ContaService contaService;
    RendasService rendasService;
    GastosService gastosService;

    @Bean
    public ContaService contaService(){
        ContaManager contaManager = new ContaManager(contaRepository);
        return contaManager;
    }

    @Bean
    public RendasService rendasService(){
        RendasManager rendasManager = new RendasManager(rendasRepository);
        return rendasManager;
    }

    @Bean
    public GastosService gastosService(){
        GastosManager gastosManager = new GastosManager(gastosRepository);
        return gastosManager;
    }

    public OrigensController() {
        this.contaService = contaService();
        this.rendasService = rendasService();
        this.gastosService = gastosService();
    }

    @PostMapping("/carregarOrigem")
    public ResponseEntity<?> listarOrigens(@RequestBody String user){
        logger.info(String.format("Entrou no método carregarOrigem"));
        contaService.setContaRepository(contaRepository);
        Integer usuario = Integer.parseInt(user);
        List<Conta> contaList = contaService.findAll(usuario);
        List<OrigensDTO> origensDTOList = new ArrayList<>();
        if (contaList.size() > 0){
            for (Conta conta : contaList){
                OrigensDTO origensDTO = new OrigensDTO();
                BancoDescr bancoDescr = new BancoDescr();
                origensDTO.setOrigem(conta.getId() + " - " + bancoDescr.getBanco(conta.getBanco()) + " - " + conta.getAgencia() + " - " + conta.getConta());
                origensDTO.setSaldo(conta.getSaldo());
                origensDTO.setId(conta.getId());
                origensDTOList.add(origensDTO);
            }
            return ResponseEntity.ok(origensDTOList);
        }
        else{
            return  ResponseEntity.ok(null);
        }
    }
}
