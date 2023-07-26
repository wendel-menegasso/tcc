package br.com.mba.engenharia.de.software.service.contas;

import br.com.mba.engenharia.de.software.ConfigContas;
import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepositoryNovo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service
public class ContaService{

    @Autowired
    private ContaRepositoryNovo contasRepository;

    @PersistenceContext
    EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);

    public ContaService(ContaRepositoryNovo contasRepository) {
        this.contasRepository = contasRepository;
        ConfigContas configContas = new ConfigContas();
        this.entityManager = configContas.entityManagerFactory().createEntityManager();
    }

    @Transactional
    public boolean salvarConta(Conta contas) {
        List<Conta> list = contasRepository.lastRegister();
        int id = list.get(list.size()).getId();
        contas.setId(id);
        contasRepository.save(contas);
        return true;
    }

    @Transactional
    public List<Conta> procuraRegistro(Conta contas){
        if (contas.getConta() != null && contas.getAgencia() != null && contas.getBanco() != null){
            return contasRepository.findByContaAgenciaBanco(contas.getConta(), contas.getAgencia(), contas.getBanco());
        }
        else if (contas.getConta() != null && contas.getAgencia() != null){
            return contasRepository.findByContaAgencia(contas.getConta(),contas.getAgencia());
        }
        else if (contas.getConta() != null && contas.getBanco() != null){
            return contasRepository.findByContaBanco(contas.getConta(), contas.getBanco());
        }
        else if (contas.getAgencia() != null && contas.getBanco() != null){
            return contasRepository.findByAgenciaBanco(contas.getAgencia(), contas.getBanco());
        } else if (contas.getConta() != null) {
            return contasRepository.findByConta(contas.getConta());
        } else if (contas.getAgencia() != null) {
            return contasRepository.findByAgencia(contas.getAgencia());
        }
        else if (contas.getBanco() != null){
            return contasRepository.findByBanco(contas.getBanco());
        }
        return null;
    }

    public List<Conta> listarConta(Conta contas){
        return procuraRegistro(contas);
    }

    public List<Conta> listarTodasContas(){
       return contasRepository.lastRegister();
    }


}


