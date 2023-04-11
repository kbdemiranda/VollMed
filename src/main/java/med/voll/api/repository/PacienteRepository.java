package med.voll.api.repository;

import med.voll.api.model.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("select p from Paciente p where p.ativo = true")
    Page<Paciente> listarPacientes(Pageable pageable);

    @Modifying
    @Query("update Paciente p set p.ativo = false where p.id = ?1")
    void desativarPaciente(Long id);

}
