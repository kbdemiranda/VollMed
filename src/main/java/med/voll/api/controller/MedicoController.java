package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import med.voll.api.model.medico.DadosAtualizacaoMedico;
import med.voll.api.model.medico.DadosCadastroMedico;
import med.voll.api.service.MedicoService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@SecurityRequirement(name = "bearer-key")
@Tag(name = "Médicos", description = "Endpoint de gerenciamento dos médicos do sistema")

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<?> obterMedicos(Pageable pageable){
        var medicos = medicoService.obterMedicos(pageable);
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharMedico(@PathVariable Long id){
        var medico = medicoService.detalharMedico(id);
        return ResponseEntity.ok(medico);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico){
        var medico = medicoService.cadastrarMedico(dadosCadastroMedico);
        return ResponseEntity.created(URI.create("/medicos")).body(medico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizarMedico(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico){
        var medico = medicoService.atualizarMedico(id, dadosAtualizacaoMedico);
        return ResponseEntity.created(URI.create("/medicos")).body(medico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> desabilitarMedico(@PathVariable Long id){
        var medico = medicoService.excluirMedico(id);
        return ResponseEntity.accepted().body(medico);
    }
}
