package med.voll.api.repository;

import med.voll.api.model.medico.Especialidade;
import med.voll.api.model.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("select m from Medico m where m.ativo = true")
    Page<Medico> listarMedicos(Pageable pageable);

    @Modifying
    @Query("update Medico m set m.ativo = false where m.id = ?1")
    void desativarMedico(Long id);

    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
            """)
    Boolean findAtivoById(Long id);

    @Query("""
            select m from Medico m
            where
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
                and
                c.motivoCancelamento is null
            )
            order by rand()
            limit 1
        """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);}
