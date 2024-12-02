package br.grupointegrado.educacional.repository;

import br.grupointegrado.educacional.model.Matricula;
import br.grupointegrado.educacional.model.MatriculaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, MatriculaPK> {
}
