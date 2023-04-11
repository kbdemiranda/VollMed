package med.voll.api.service.impl;

import med.voll.api.model.endereco.Endereco;
import med.voll.api.model.paciente.*;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Page<DadosListagemPaciente> obterPacientes(Pageable pageable) {
        return pacienteRepository.listarPacientes(pageable).map(DadosListagemPaciente::new);
    }

    @Override
    public DadosDetalhamentoPaciente detalharPaciente(Long id) {
        var paciente = getPaciente(id);
        return new DadosDetalhamentoPaciente(paciente);
    }

    @Override
    public Paciente cadastrarPaciente(DadosCadastroPaciente dadosCadastroPaciente) {
        Paciente paciente = new Paciente(dadosCadastroPaciente);
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente atualizarPaciente(Long id, DadosAtualizacaoPaciente dadosAtualizacaoPaciente) {
        Paciente paciente = getPaciente(id);
        paciente.setNome(dadosAtualizacaoPaciente.nome());
        paciente.setTelefone(dadosAtualizacaoPaciente.telefone());
        paciente.setEndereco(new Endereco(dadosAtualizacaoPaciente.endereco()));
        return paciente;
    }

    @Override
    public Paciente excluirPaciente(Long id) {
        Paciente paciente = getPaciente(id);
        pacienteRepository.desativarPaciente(paciente.getId());
        return paciente;
    }

    private Paciente getPaciente(Long id){
        return pacienteRepository.findById(id).orElseThrow();
    }
}
