package br.com.mba.engenharia.de.software.service.rendas;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.repository.rendas.RendasRepository;

import java.util.List;

public interface RendasService {
    int delete(Integer id);

    Renda findById(Integer id);

    List<Renda> findAll();

    int count();

    void save(Renda rendas);

    void setRendasRepository(RendasRepository repository);

    int updateRendas(String nome, Integer tipo, Double valor, String data, Integer id);

}
