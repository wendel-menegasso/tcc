package br.com.mba.engenharia.de.software.refactoring.repository.contas;

import br.com.mba.engenharia.de.software.refactoring.entity.contas.Conta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ContaRepository extends Repository<Conta, Long> {
    @Transactional
    @Modifying
    @Query("update Conta c set c.banco = ?1, c.tipo = ?2, c.saldo = ?3, c.agencia = ?4, c.conta = ?5 where c.id = ?6 and c.usuario = ?7")
    Integer updateConta(Integer banco, Integer tipo, String saldo, String agencia, String conta, Integer id, Integer usuario);
    @Transactional
    @Modifying
    @Query("delete from Conta c where c.id = ?1")
    Integer delete(Integer id);
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

    @Query("select c from Conta c where c.id = ?1")
    Conta findById(Integer id);

    @Query("select c from Conta c where c.usuario = ?1")
    List<Conta> findAll(Integer usuario);

    @Query("select count(c) from Conta c")
    int count();

    Conta save(Conta contas);
}