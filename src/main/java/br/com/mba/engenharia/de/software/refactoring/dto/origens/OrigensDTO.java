package br.com.mba.engenharia.de.software.refactoring.dto.origens;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrigensDTO {
    private Integer id;
    private String origem;
    private String saldo;
}
