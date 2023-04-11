package med.voll.api.model.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.voll.api.model.endereco.Endereco;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "pacientes", schema = "vollmed")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "cpf")
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo = true;

    public Paciente(DadosCadastroPaciente dadosCadastroMedico) {
        this.nome = dadosCadastroMedico.nome();
        this.email = dadosCadastroMedico.email();
        this.telefone = dadosCadastroMedico.telefone();
        this.cpf = dadosCadastroMedico.cpf();
        this.endereco = new Endereco(dadosCadastroMedico.endereco());
    }
}
