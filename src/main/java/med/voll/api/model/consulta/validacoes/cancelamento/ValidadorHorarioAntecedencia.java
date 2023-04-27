package med.voll.api.model.consulta.validacoes.cancelamento;


import med.voll.api.model.ValidacaoException;
import med.voll.api.model.consulta.DadosCancelamentoConsulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    private final ConsultaRepository consultaRepository;

    public ValidadorHorarioAntecedencia(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Override
    public void validar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
