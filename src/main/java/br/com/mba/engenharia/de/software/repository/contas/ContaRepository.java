package br.com.mba.engenharia.de.software.repository.contas;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ContaRepository extends Repository<Conta, Long> {
    @Query("select c from Conta c where c.banco = ?1")
    List<Conta> findByBanco(Integer banco);

    @Query("select c from Conta c where c.agencia = ?1")
    List<Conta> findByAgencia(String agencia);

    @Query("select c from Conta c where c.conta = ?1")
    List<Conta> findByConta(String conta);

    @Query("select c from Conta c where c.agencia = ?1 and c.banco = ?2")
    List<Conta> findByAgenciaBanco(String agencia, Integer banco);

    @Query("select c from Conta c where c.banco = ?1")
    List<Conta> findByContaBanco(String conta, Integer banco);

    @Query("select c from Conta c where c.conta = ?1 and c.agencia = ?2")
    List<Conta> findByContaAgencia(String conta, String agencia);

    @Query("select c from Conta c where c.conta = ?1 and c.agencia = ?2 and c.banco = ?3")
    List<Conta> findByContaAgenciaBanco(String conta, String agencia, Integer banco);

    List<Conta> findAll();

    void save(Conta contas);
}