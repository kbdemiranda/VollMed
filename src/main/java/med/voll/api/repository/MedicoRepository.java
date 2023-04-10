package med.voll.api.repository;

import med.voll.api.model.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("select m from Medico m where m.ativo = true")
    Page<Medico> listarMedicos(Pageable pageable);

    @Modifying
    @Query("update Medico m set m.ativo = false where m.id = ?1")
    void desativarMedico(Long id);

}
