package br.grupointegrado.educacional.repository;

import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.model.Matricula;
import br.grupointegrado.educacional.model.MatriculaPK;
import br.grupointegrado.educacional.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, MatriculaPK> {

    Optional<Matricula> findByMatriculaPK_AlunoAndMatriculaPK_Turma(Aluno aluno, Turma turma);
}
