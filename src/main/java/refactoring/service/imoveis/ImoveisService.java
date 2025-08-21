package refactoring.service.imoveis;

import refactoring.entity.imoveis.Imoveis;
import refactoring.repository.imoveis.ImoveisRepository;

import java.util.List;

public interface ImoveisService {
    int delete(Integer id);
    Imoveis findById(Integer id);
    List<Imoveis> findAll(Integer idUser);
    int count();
    Imoveis save(Imoveis imoveis);
    void setRepository(ImoveisRepository repository);
}
