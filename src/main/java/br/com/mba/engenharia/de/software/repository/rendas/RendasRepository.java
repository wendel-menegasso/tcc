package br.com.mba.engenharia.de.software.repository.rendas;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Repository
public interface RendasRepository extends Repository<Renda, Long> {
    @Transactional
    @Modifying
    @Query("delete from Renda r where r.id = ?1")
    int delete(Integer id);

    @Query("select r from Renda r where r.id = ?1")
    Renda findById(Integer id);

    List<Renda> findAll();

    @Query("select count(r) from Renda r")
    int count();

    void save(Renda rendas);

    @Transactional
    @Modifying
    @Query("update Renda r set r.nome = ?1, r.tipo = ?2, r.valor = ?3, r.data = ?4 where r.id = ?5")
    int updateRendas(String nome, Integer tipo, Double valor, String data, Integer id);

}
