package med.voll.api.service.impl;

import med.voll.api.model.endereco.Endereco;
import med.voll.api.model.medico.*;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public Page<DadosListagemMedico> obterMedicos(Pageable pageable) {
        return medicoRepository.listarMedicos(pageable).map(DadosListagemMedico::new);
    }

    @Override
    public DadosDetalhamentoMedico detalharMedico(Long id) {
        var medico = getMedico(id);
        return new DadosDetalhamentoMedico(medico);
    }

    @Override
    public Medico cadastrarMedico(DadosCadastroMedico dadosCadastroMedico) {
        Medico medico = new Medico(dadosCadastroMedico);
        return medicoRepository.save(medico);
    }

    @Override
    public Medico atualizarMedico(Long id, DadosAtualizacaoMedico dadosAtualizacaoMedico) {
        var medico = getMedico(id);
        medico.setNome(dadosAtualizacaoMedico.nome());
        medico.setTelefone(dadosAtualizacaoMedico.telefone());
        medico.setEndereco(new Endereco(dadosAtualizacaoMedico.endereco()));
        return medicoRepository.save(medico);
    }

    @Override
    public Medico excluirMedico(Long id) {
        medicoRepository.desativarMedico(id);
        return getMedico(id);
    }

    private Medico getMedico(Long id){
        return medicoRepository.findById(id).orElseThrow();
    }
}
