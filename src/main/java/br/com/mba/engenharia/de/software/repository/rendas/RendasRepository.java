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

    @Query("select count(r) from Renda c")
    int count();

    void save(Renda rendas);
}
