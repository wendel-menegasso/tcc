package br.com.mba.engenharia.de.software.repository;

import br.com.mba.engenharia.de.software.negocio.contas.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasRepository extends JpaRepository<Conta, Integer> {
}