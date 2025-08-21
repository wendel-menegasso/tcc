package refactoring.repository.gastos;

import refactoring.entity.despesas.Gastos;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Repository
public interface GastosRepository extends Repository<Gastos, Long> {
    @Transactional
    @Modifying
    @Query("delete from Gastos g where g.id = ?1")
    int delete(Integer id);

    @Query("select g from Gastos g where g.id = ?1")
    Gastos findById(Integer id);

    @Query("select g from Gastos g where g.usuario = ?1")
    List<Gastos> findAll(Integer idUser);

    @Query("select count(g) from Gastos g")
    int count();

    Gastos save(Gastos gastos);

    @Transactional
    @Modifying
    @Query("update Gastos g set g.nome = ?1, g.tipo = ?2, g.valor = ?3, g.data = ?4 where g.id = ?5")
    int updateGastos(String nome, Integer tipo, String valor, String data, Integer id);
}
