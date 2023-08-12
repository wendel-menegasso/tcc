package br.com.mba.engenharia.de.software.service.rendas;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
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
    public int delete(Integer id) {
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
    public void save(Renda rendas) {
        repository.save(rendas);
    }

    @Override
    public void setRendasRepository(RendasRepository repository) {
        this.repository = repository;
    }
}
