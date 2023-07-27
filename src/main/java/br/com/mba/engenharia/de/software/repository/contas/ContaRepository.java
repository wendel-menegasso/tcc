package br.com.mba.engenharia.de.software.repository.contas;

import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ContaRepository<Conta, Long> extends Repository<Conta, Long> {
    List<Conta> findByBanco(Integer banco);
    List<Conta> findByAgencia(String agencia);
    List<Conta> findByConta(String conta);
    List<Conta> findByAgenciaBanco(String agencia, Integer banco);
    List<Conta> findByContaBanco(String conta, Integer banco);
    List<Conta> findByContaAgencia(String conta, String agencia);
    List<Conta> findByContaAgenciaBanco(String conta, String agencia, Integer banco);
    List<Conta> lastRegister();
    void save(Conta contas);
}