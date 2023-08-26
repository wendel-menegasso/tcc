package br.com.mba.engenharia.de.software.service.rendas;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.repository.rendas.RendasRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RendasManager implements RendasService{

    private RendasRepository repository;

    @Autowired
    public RendasManager(RendasRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Renda> delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Renda findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Renda> findAll() {
        return repository.findAll();
    }

    @Override
    public int count() {
        return repository.count() + 1;
    }

    @Override
    public Renda save(Renda rendas) {
        return repository.save(rendas);
    }

    @Override
    public void setRendasRepository(RendasRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Renda> updateRendas(String nome, Integer tipo, Double valor, String data, Integer id) {
        return this.repository.updateRendas(nome, tipo, valor, data, id);
    }
}
