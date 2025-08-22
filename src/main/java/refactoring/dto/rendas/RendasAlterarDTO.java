package refactoring.dto.rendas;

import refactoring.entity.rendas.Renda;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RendasAlterarDTO {
    @NotNull
    private Integer id;
    private String data;
    private String nome;
    private Integer tipo;
    private Integer usuario;
    private Integer origem;
    private String valor;

    public Renda parseRendasDTOToRenda(){
        String valorAtualizado = parseSaldoToCurrencyValue(this.valor);
        return new Renda(nome, valorAtualizado, data, tipo,id, usuario, origem);
    }

    public String parseSaldoToCurrencyValue(String saldoAntigo){
        if (saldoAntigo.indexOf(".") == saldoAntigo.length() -3) {
            return saldoAntigo;
        }
        if (saldoAntigo.indexOf(".") == saldoAntigo.length() -2){
            return saldoAntigo + "0";
        }
        return saldoAntigo + ".00";
    }

}
