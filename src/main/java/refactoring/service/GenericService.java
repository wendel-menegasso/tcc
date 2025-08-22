package refactoring.service;

import refactoring.dto.contas.ContaDTOAlterar;
import refactoring.dto.contas.ContaDTOAlterarFull;
import refactoring.dto.contas.ContaDTORetorno;
import refactoring.entity.contas.Conta;
import refactoring.entity.imoveis.Imoveis;
import refactoring.output.CSVHelper;
import refactoring.repository.contas.ContaRepository;
import refactoring.repository.gastos.GastosRepository;
import refactoring.repository.imoveis.ImoveisRepository;
import refactoring.repository.rendas.RendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class GenericService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ImoveisRepository repository;

    @Autowired
    RendasRepository rendasRepository;

    @Autowired
    GastosRepository gastosRepository;

    private int usuario;

    public void setUsuario(int i) {
        this.usuario = usuario;
    }

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

    public ByteArrayInputStream load(String filename) throws IOException {
        List<Imoveis> imoveis = repository.findAll(usuario);

        ByteArrayInputStream in = CSVHelper.imovelToCSV(imoveis,filename);
        return in;
    }

    public double pegaDadosDasRendas(String usuario){
        return rendasRepository.findAll(Integer.parseInt(usuario)).stream()
                .mapToDouble(renda -> Double.parseDouble(renda.getValor()))
                .sum();

    }

    public double pegaDadosDosGastos(String usuario){
        return gastosRepository.findAll(Integer.parseInt(usuario))
                .stream()
                .mapToDouble(gastos->Double.parseDouble(gastos.getValor()))
                .sum();
    }
}
