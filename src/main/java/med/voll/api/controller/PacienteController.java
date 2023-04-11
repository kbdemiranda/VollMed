package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.paciente.DadosAtualizacaoPaciente;
import med.voll.api.model.paciente.DadosCadastroPaciente;
import med.voll.api.service.PacienteService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<?> obterPacientes(Pageable pageable){
        var Pacientes = pacienteService.obterPacientes(pageable);
        return ResponseEntity.ok(Pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharPaciente(@PathVariable Long id){
        var Paciente = pacienteService.detalharPaciente(id);
        return ResponseEntity.ok(Paciente);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente){
        var Paciente = pacienteService.cadastrarPaciente(dadosCadastroPaciente);
        return ResponseEntity.created(URI.create("/Pacientes")).body(Paciente);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizarPaciente(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoPaciente dadosAtualizacaoPaciente){
        var Paciente = pacienteService.atualizarPaciente(id, dadosAtualizacaoPaciente);
        return ResponseEntity.created(URI.create("/Pacientes")).body(Paciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> desabilitarPaciente(@PathVariable Long id){
        var Paciente = pacienteService.excluirPaciente(id);
        return ResponseEntity.accepted().body(Paciente);
    }
}
