package br.com.mba.engenharia.de.software.service.contas;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContaService {
    List<Conta> findByBanco(Integer banco);
    List<Conta> findByAgencia(String agencia);
    List<Conta> findByConta(String conta);
    List<Conta> findByAgenciaBanco(String agencia, Integer banco);
    List<Conta> findByContaBanco(String conta, Integer banco);
    List<Conta> findByContaAgencia(String conta, String agencia);
    List<Conta> findByContaAgenciaBanco(String conta, String agencia, Integer banco);
    List<Conta> lastRegister();
    void save(Conta contas);
    void setContaRepository(ContaRepository contaRepository);
    int count();
    List<Conta> findAll();
}
