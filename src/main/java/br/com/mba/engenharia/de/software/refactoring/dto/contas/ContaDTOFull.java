package br.com.mba.engenharia.de.software.refactoring.dto.contas;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ContaDTOFull {
    @NotNull
    @Pattern(regexp = "[0-9]")
    private String usuario;

    public String getUsuario() {
        return usuario;
    }
}
