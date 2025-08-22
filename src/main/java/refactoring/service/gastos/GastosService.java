package refactoring.service.gastos;

import refactoring.entity.despesas.Gastos;
import refactoring.repository.gastos.GastosRepository;

import java.util.List;

public interface GastosService {
    int delete(Integer id);
    Gastos findById(Integer id);
    List<Gastos> findAll(Integer idUser);
    int count();
    Gastos save(Gastos gastos);
    int updateGastos(String nome, Integer tipo, String valor, String data, Integer id);
    void setGastosRepository(GastosRepository repository);
}
