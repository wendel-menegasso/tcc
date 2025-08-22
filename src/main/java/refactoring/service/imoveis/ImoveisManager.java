package refactoring.service.imoveis;

import refactoring.entity.imoveis.Imoveis;
import refactoring.repository.imoveis.ImoveisRepository;

import java.util.List;

public class ImoveisManager implements ImoveisService {

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
