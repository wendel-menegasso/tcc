package br.com.mba.engenharia.de.software.repository.imoveis;

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

    @Transactional
    @Modifying
    @Query("update Imoveis i set i.cep = ?1, i.logradouro= ?2, i.rua = ?3, i.numero = ?4, i.tipoImovel = ?5, i.bairro = ?6, i.cidade = ?7, i.estado = ?8, i.pais = ?9 " +
            "where i.usuario = ?10 and i.id = ?11")
    Integer updateImoveis(String cep, int logradouro, String rua, int numero, int tipoImovel, String bairro, String cidade, String estado, String pais, int usuario, int id);

}
