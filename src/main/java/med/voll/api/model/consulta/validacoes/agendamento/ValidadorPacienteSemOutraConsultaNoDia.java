package med.voll.api.model.consulta.validacoes.agendamento;

import med.voll.api.model.ValidacaoException;
import med.voll.api.model.consulta.DadosAgendamentoConsulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    private final ConsultaRepository consultaRepository;

    public ValidadorPacienteSemOutraConsultaNoDia(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }

}
