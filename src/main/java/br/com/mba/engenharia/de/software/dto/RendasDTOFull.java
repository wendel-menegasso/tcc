package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RendasDTOFull {
    private String id;
    private String usuario;
    private String page;
    private String size;

    public Renda parseRendasDTOToRenda(){
        return new Renda(Integer.parseInt(id), Integer.parseInt(usuario));
    }

}
