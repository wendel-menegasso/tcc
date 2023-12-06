package br.com.mba.engenharia.de.software.service.veiculos;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.entity.veiculos.Veiculos;
import br.com.mba.engenharia.de.software.repository.veiculos.VeiculosRepository;
import org.springframework.data.jpa.repository.Query;

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
