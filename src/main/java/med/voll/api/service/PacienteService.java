package med.voll.api.service;

import med.voll.api.model.paciente.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteService {

    Page<DadosListagemPaciente> obterPacientes(Pageable pageable);
    DadosDetalhamentoPaciente detalharPaciente(Long id);
    Paciente cadastrarPaciente(DadosCadastroPaciente dadosCadastroPaciente);
    Paciente atualizarPaciente(Long id, DadosAtualizacaoPaciente dadosAtualizacaoPaciente);
    Paciente excluirPaciente(Long id);

}
