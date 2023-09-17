package br.com.mba.engenharia.de.software.service.gastos;

import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import br.com.mba.engenharia.de.software.repository.gastos.GastosRepository;

import java.math.BigDecimal;
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
