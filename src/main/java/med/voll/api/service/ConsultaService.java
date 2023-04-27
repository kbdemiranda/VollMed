package med.voll.api.service;

import med.voll.api.model.consulta.DadosAgendamentoConsulta;
import med.voll.api.model.consulta.DadosCancelamentoConsulta;
import med.voll.api.model.consulta.DadosDetalhamentoConsulta;

public interface ConsultaService {

    DadosDetalhamentoConsulta agendarConsulta(DadosAgendamentoConsulta dadosAgendamentoConsulta);
    void cancelarConsulta(DadosCancelamentoConsulta dadosCancelamentoConsulta);
}
