package br.com.mba.engenharia.de.software.dto.contas;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTOAlterar {
    @NotNull
    @Pattern(regexp = "[0-9]")
    private String id;
    @NotNull
    private Integer usuario;
    public Conta parseContaDTOAlterarToConta(){
        return new Conta(Integer.parseInt(this.id), this.usuario);
    }

}
