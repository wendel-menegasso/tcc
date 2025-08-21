package refactoring.service.gastos;

import refactoring.entity.despesas.Gastos;
import refactoring.repository.gastos.GastosRepository;

import java.util.List;

public class GastosManager implements GastosService {

    private GastosRepository repository;

    public GastosManager(GastosRepository repository){
        this.repository = repository;
    }

    @Override
    public int delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Gastos findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Gastos> findAll(Integer idUser) {
        return repository.findAll(idUser);
    }

    @Override
    public int count() {
        return repository.count() + 1;
    }

    @Override
    public Gastos save(Gastos gastos) {
        return repository.save(gastos);
    }

    @Override
    public int updateGastos(String nome, Integer tipo, String valor, String data, Integer id) {
        return repository.updateGastos(nome, tipo, valor, data, id);
    }

    @Override
    public void setGastosRepository(GastosRepository repository) {
        this.repository = repository;
    }
}
