package br.com.mba.engenharia.de.software.refactoring.repository.veiculos;

import br.com.mba.engenharia.de.software.entity.veiculos.Veiculos;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Repository
public interface VeiculosRepository extends Repository<Veiculos, Long> {
    @Query("select count(v) from Veiculos v")
    int count();
    Veiculos save(Veiculos veiculos);
    @Query("select v from Veiculos v where v.id = ?1")
    Veiculos findById(Integer id);
    @Query("select v from Veiculos v where v.usuario = ?1")
    List<Veiculos> findAll(Integer idUser);
    @Transactional
    @Modifying
    @Query("delete from Veiculos v where v.id = ?1")
    Integer delete(Integer id);
    @Transactional
    @Modifying
    @Query("update Veiculos v set v.placa = ?1, v.modelo = ?2, v.marca = ?3, v.ano = ?4 where v.id = ?5 and v.usuario = ?6")
    Integer updateVeiculos(String placa, String modelo, String marca, int ano, Integer id, Integer usuario);
}
