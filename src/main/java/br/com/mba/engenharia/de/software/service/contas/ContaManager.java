package br.com.mba.engenharia.de.software.service.contas;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaManager implements ContaService{

    private ContaRepository contasRepository;

    private static final Logger logger = LoggerFactory.getLogger(ContaManager.class);

    @Autowired
    public ContaManager(ContaRepository contasRepository) {
        this.contasRepository = contasRepository;
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


