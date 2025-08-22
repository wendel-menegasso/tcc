package refactoring.service.veiculos;

import refactoring.entity.veiculos.Veiculos;
import refactoring.repository.veiculos.VeiculosRepository;

import java.util.List;

public class VeiculosManager implements VeiculosService {

    private VeiculosRepository repository;

    @Override
    public void setVeiculosRepository(VeiculosRepository repository) {
        this.repository = repository;
    }

    @Override
    public int count() {
        return repository.count();
    }

    @Override
    public List<Veiculos> findAll(int usuario) {
        return repository.findAll(usuario);
    }

    @Override
    public Veiculos save(Veiculos veiculos) {
        return repository.save(veiculos);
    }

    @Override
    public Veiculos findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Integer delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Integer updateVeiculos(String placa, String modelo, String marca, int ano, Integer id, Integer usuario) {
        return repository.updateVeiculos(placa, modelo, marca, ano, id, usuario);
    }
}
