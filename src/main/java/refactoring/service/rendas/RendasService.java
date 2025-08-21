package refactoring.service.rendas;

import refactoring.entity.rendas.Renda;
import refactoring.repository.rendas.RendasRepository;

import java.util.List;

public interface RendasService {
    Integer delete(Integer id);

    Renda findById(Integer id);

    List<Renda> findAll(Integer idUser);

    int count();

    Renda save(Renda rendas);

    void setRendasRepository(RendasRepository repository);

    Integer updateRendas(String nome, Integer tipo, String valor, String data, Integer id, Integer usuario);

}
