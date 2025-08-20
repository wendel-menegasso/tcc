package br.com.mba.engenharia.de.software.refactoring.service.contas;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.refactoring.repository.contas.ContaRepository;
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
    Conta save(Conta contas);
    Integer delete(int id);
    void setContaRepository(ContaRepository contaRepository);
    int count();
    List<Conta> findAll(Integer usuario);
    Conta findById(Integer id);
    Integer updateConta(Integer banco, Integer tipo, String saldo, String agencia, String conta, Integer id, Integer usuario);
}
