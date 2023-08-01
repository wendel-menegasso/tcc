package br.com.mba.engenharia.de.software.service.contas;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaManager implements ContaService{

    private ContaRepository contasRepository;

    private static final Logger logger = LoggerFactory.getLogger(ContaManager.class);

    @Autowired
    public ContaManager(ContaRepository contasRepository) {
        this.contasRepository = contasRepository;
    }


    @Transactional
    public boolean salvarConta(Conta contas) {
        List<Conta> list = contasRepository.findAll();
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

    @Transactional
    public List<Conta> listarTodasContas(){
       return contasRepository.findAll();
    }


    @Override
    public List<Conta> findByBanco(Integer banco) {
        return contasRepository.findByBanco(banco);
    }

    @Override
    public List<Conta> findByAgencia(String agencia) {
        return contasRepository.findByAgencia(agencia);
    }

    @Override
    public List<Conta> findByConta(String conta) {
        return contasRepository.findByConta(conta);
    }

    @Override
    public List<Conta> findByAgenciaBanco(String agencia, Integer banco) {
        return contasRepository.findByAgenciaBanco(agencia, banco);
    }

    @Override
    public List<Conta> findByContaBanco(String conta, Integer banco) {
        return contasRepository.findByContaBanco(conta, banco);
    }

    @Override
    public List<Conta> findByContaAgencia(String conta, String agencia) {
        return contasRepository.findByContaAgencia(conta, agencia);
    }

    @Override
    public List<Conta> findByContaAgenciaBanco(String conta, String agencia, Integer banco) {
        return contasRepository.findByContaAgenciaBanco(conta, agencia, banco);
    }

    @Override
    public List<Conta> lastRegister() {
        return contasRepository.findAll();
    }

    @Override
    public void save(Conta contas) {
        contasRepository.save(contas);
    }

    @Override
    public void setContaRepository(ContaRepository contaRepository) {
        this.contasRepository = contaRepository;
    }

    @Override
    public int count() {
        return this.contasRepository.count() + 1;
    }

    @Override
    public List<Conta> findAll() {
        return this.contasRepository.findAll();
    }

    @Override
    public Conta findById(Integer id) {
        return this.contasRepository.findById(id);
    }
}


