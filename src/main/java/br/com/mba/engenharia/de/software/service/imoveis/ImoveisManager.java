package br.com.mba.engenharia.de.software.service.imoveis;

import br.com.mba.engenharia.de.software.entity.imoveis.Imoveis;
import br.com.mba.engenharia.de.software.repository.imoveis.ImoveisRepository;

import java.util.List;

public class ImoveisManager implements ImoveisService{

    private ImoveisRepository repository;

    public ImoveisManager(ImoveisRepository repository){
        this.repository = repository;
    }

    @Override
    public int delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Imoveis findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Imoveis> findAll(Integer idUser) {
        return repository.findAll(idUser);
    }

    @Override
    public int count() {
        return repository.count();
    }

    @Override
    public Imoveis save(Imoveis imoveis) {
        return repository.save(imoveis);
    }

    @Override
    public void setRepository(ImoveisRepository repository) {
        this.repository = repository;
    }
}
