package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import med.voll.api.model.consulta.DadosAgendamentoConsulta;
import med.voll.api.model.consulta.DadosCancelamentoConsulta;
import med.voll.api.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@Tag(name = "Consultas", description = "Endpoint para controle de consultas")
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = consultaService.agendarConsulta(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        consultaService.cancelarConsulta(dados);
        return ResponseEntity.noContent().build();
    }

}
