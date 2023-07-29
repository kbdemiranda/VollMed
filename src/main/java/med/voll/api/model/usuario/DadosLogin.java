package med.voll.api.model.usuario;

import jakarta.validation.constraints.NotEmpty;

public record DadosLogin(
        @NotEmpty
        String email,
        @NotEmpty
        String senha) {
}
