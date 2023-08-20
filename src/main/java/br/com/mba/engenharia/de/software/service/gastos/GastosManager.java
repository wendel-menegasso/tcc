package br.com.mba.engenharia.de.software.service.gastos;

import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import br.com.mba.engenharia.de.software.repository.gastos.GastosRepository;

import java.util.List;

public class GastosManager implements GastosService{

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
    public List<Gastos> findAll() {
        return repository.findAll();
    }

    @Override
    public int count() {
        return repository.count();
    }

    @Override
    public Gastos save(Gastos gastos) {
        return repository.save(gastos);
    }

    @Override
    public Gastos updateGastos(String nome, Integer tipo, Double valor, String data, Integer id) {
        return repository.updateGastos(nome, tipo, valor, data, id);
    }

    @Override
    public void setGastosRepository(GastosRepository repository) {
        this.repository = repository;
    }
}
