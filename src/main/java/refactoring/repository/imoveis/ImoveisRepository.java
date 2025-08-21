package refactoring.repository.imoveis;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import refactoring.entity.imoveis.Imoveis;

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

    @Transactional
    @Modifying
    @Query("update Imoveis i set i.cep = ?1, i.logradouro= ?2, i.rua = ?3, i.numero = ?4, i.bairro = ?5, i.cidade = ?6, i.estado = ?7, i.pais = ?8 " +
            "where i.usuario = ?9 and i.id = ?10")
    Integer updateImoveis(String cep, String logradouro, String rua, String numero, String bairro, String cidade, String estado, String pais, int usuario, int id);

}
