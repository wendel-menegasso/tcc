package refactoring.dto.rendas;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class RendaDTOFull {
    @NotNull
    @Pattern(regexp = "[0-9]")
    private String usuario;
}
