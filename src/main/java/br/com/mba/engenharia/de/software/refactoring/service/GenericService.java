package br.com.mba.engenharia.de.software.refactoring.service;

import br.com.mba.engenharia.de.software.refactoring.dto.contas.ContaDTOAlterar;
import br.com.mba.engenharia.de.software.refactoring.dto.contas.ContaDTOAlterarFull;
import br.com.mba.engenharia.de.software.refactoring.dto.contas.ContaDTORetorno;
import br.com.mba.engenharia.de.software.refactoring.entity.contas.Conta;
import br.com.mba.engenharia.de.software.refactoring.repository.contas.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenericService {

    @Autowired
    ContaRepository contaRepository;

    public ContaDTORetorno pegaConta(ContaDTOAlterar contaDTOAlterar) {
        Conta conta = contaDTOAlterar.parseContaDTOAlterarToConta();
        Conta contaRetorno = contaRepository.findById(conta.getId());
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        return contaDTORetorno;
    }

    public ContaDTORetorno atualizaConta(ContaDTOAlterarFull contaDTOAlterarFull) {
        Conta conta = contaDTOAlterarFull.parseContaDTOToConta();
        Conta contaRetorno = contaRepository.findById(conta.getId());
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        Integer numeroDeRegistrosAlterados = contaRepository.updateConta(conta.getBanco(), conta.getTipo(), conta.getSaldo(), conta.getAgencia(), conta.getConta(), conta.getId(), conta.getUsuario());
        if (numeroDeRegistrosAlterados > 0) {
            return contaDTORetorno;
        } else {
            return null;
        }
    }
}
