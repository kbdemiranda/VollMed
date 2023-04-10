package med.voll.api.service;

import med.voll.api.model.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicoService {

    Page<DadosListagemMedico> obterMedicos(Pageable pageable);
    DadosDetalhamentoMedico detalharMedico(Long id);
    Medico cadastrarMedico(DadosCadastroMedico dadosCadastroMedico);
    Medico atualizarMedico(Long id, DadosAtualizacaoMedico dadosAtualizacaoMedico);
    Medico excluirMedico(Long id);
}
