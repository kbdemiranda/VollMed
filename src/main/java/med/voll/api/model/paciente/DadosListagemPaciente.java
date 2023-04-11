package med.voll.api.model.paciente;

import med.voll.api.model.medico.Especialidade;
import med.voll.api.model.medico.Medico;

public record DadosListagemPaciente(
        Long id,
        String nome,
        String email,
        String cpf) {

    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
