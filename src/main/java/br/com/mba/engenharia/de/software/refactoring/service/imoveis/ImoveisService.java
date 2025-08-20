package br.com.mba.engenharia.de.software.refactoring.service.imoveis;

import br.com.mba.engenharia.de.software.entity.imoveis.Imoveis;
import br.com.mba.engenharia.de.software.refactoring.repository.imoveis.ImoveisRepository;

import java.util.List;

public interface ImoveisService {
    int delete(Integer id);
    Imoveis findById(Integer id);
    List<Imoveis> findAll(Integer idUser);
    int count();
    Imoveis save(Imoveis imoveis);
    void setRepository(ImoveisRepository repository);
}
