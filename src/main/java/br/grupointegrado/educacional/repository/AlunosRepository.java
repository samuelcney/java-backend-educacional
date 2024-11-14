package br.grupointegrado.educacional.repository;

import br.grupointegrado.educacional.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunosRepository extends JpaRepository<Aluno, Integer> {
}
