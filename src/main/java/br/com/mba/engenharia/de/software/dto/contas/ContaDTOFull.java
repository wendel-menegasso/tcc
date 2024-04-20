package br.com.mba.engenharia.de.software.dto.contas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTOFull {
    @NotNull
    @Pattern(regexp = "[0-9]")
    private String usuario;
}
