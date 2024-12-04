package br.grupointegrado.educacional.repository;

import br.grupointegrado.educacional.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {

    List<Nota> findByMatricula_MatriculaPK_Aluno_Id(Integer alunoId);
    List<Nota> findByMatricula_MatriculaPK_Turma_Id(Integer turmaId);
    List<Nota> findByDisciplina_Id(Integer disciplinaId);
}
