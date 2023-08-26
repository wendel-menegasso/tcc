package br.com.mba.engenharia.de.software.service.rendas;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.repository.rendas.RendasRepository;

import java.util.List;

public interface RendasService {
    List<Renda> delete(Integer id);

    Renda findById(Integer id);

    List<Renda> findAll();

    int count();

    Renda save(Renda rendas);

    void setRendasRepository(RendasRepository repository);

    List<Renda> updateRendas(String nome, Integer tipo, Double valor, String data, Integer id);

}
