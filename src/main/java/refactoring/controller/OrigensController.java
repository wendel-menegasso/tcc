package refactoring.controller;

import refactoring.dto.origens.OrigensDTO;
import refactoring.entity.contas.BancoDescr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import refactoring.repository.contas.ContaRepository;
import refactoring.repository.gastos.GastosRepository;
import refactoring.repository.rendas.RendasRepository;

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
