package br.com.mba.engenharia.de.software.service.rendas;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RendasService {
    int delete(Integer id);

    Renda findById(Integer id);

    List<Renda> findAll();

    int count();

    void save(Renda rendas);
}
