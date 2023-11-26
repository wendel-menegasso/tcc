package br.com.mba.engenharia.de.software.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class RendaDTOFull {
    @NotNull
    @Pattern(regexp = "[0-9]")
    private String usuario;
}
