package refactoring.service.veiculos;

import refactoring.entity.veiculos.Veiculos;
import refactoring.repository.veiculos.VeiculosRepository;

import java.util.List;

public interface VeiculosService {
    void setVeiculosRepository(VeiculosRepository repository);
    int count();
    List<Veiculos> findAll(int usuario);
    Veiculos save(Veiculos veiculos);
    Veiculos findById(Integer id);
    Integer delete(Integer id);
    Integer updateVeiculos(String placa, String modelo, String marca, int ano, Integer id, Integer usuario);
}
