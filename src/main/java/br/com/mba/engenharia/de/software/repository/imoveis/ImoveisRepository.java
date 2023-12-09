package br.com.mba.engenharia.de.software.repository.imoveis;

import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import br.com.mba.engenharia.de.software.entity.imoveis.Imoveis;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ImoveisRepository extends Repository<Imoveis, Long> {
    @Transactional
    @Modifying
    @Query("delete from Imoveis i where i.id = ?1")
    int delete(Integer id);

    @Query("select i from Imoveis i where i.id = ?1")
    Imoveis findById(Integer id);

    @Query("select i from Imoveis i where i.usuario = ?1")
    List<Imoveis> findAll(Integer idUser);

    @Query("select count(i) from Imoveis i")
    int count();

    Imoveis save(Imoveis imoveis);
}
