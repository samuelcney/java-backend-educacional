package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.dto.MatriculaPKDTO;
import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.model.Matricula;
import br.grupointegrado.educacional.model.MatriculaPK;
import br.grupointegrado.educacional.model.Turma;
import br.grupointegrado.educacional.repository.AlunosRepository;
import br.grupointegrado.educacional.repository.MatriculaRepository;
import br.grupointegrado.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunosRepository alunosRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public ResponseEntity<List<Matricula>> findAll() {
        return ResponseEntity.ok(this.matriculaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MatriculaPKDTO pkdto) {
        Optional<Aluno> alunoOpt = this.alunosRepository.findById(pkdto.aluno_id());
        Optional<Turma> turmaOpt = this.turmaRepository.findById(pkdto.turma_id());

        if (alunoOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Aluno não encontrado"));
        }

        if (turmaOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Turma não encontrada"));
        }

        Optional<Matricula> matriculaExistente = this.matriculaRepository
                .findByMatriculaPK_AlunoAndMatriculaPK_Turma(alunoOpt.get(), turmaOpt.get());
        if (matriculaExistente.isPresent()) {
            return ResponseEntity.status(400)
                    .body(Map.of("message", "O aluno já está cadastrado nesta turma."));
        }

        MatriculaPK matriculaPK = new MatriculaPK(alunoOpt.get(), turmaOpt.get());

        Matricula matricula = new Matricula();
        matricula.setMatriculaPK(matriculaPK);

        this.matriculaRepository.save(matricula);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(matricula);
    }

    @DeleteMapping("/{alunoId}/{turmaId}")
    public ResponseEntity<?> delete(@PathVariable Integer alunoId, @PathVariable Integer turmaId) {
        Optional<Aluno> alunoOpt = this.alunosRepository.findById(alunoId);
        Optional<Turma> turmaOpt = this.turmaRepository.findById(turmaId);

        if (alunoOpt.isEmpty() || turmaOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Aluno ou turma não encontrados."));
        }

        Optional<Matricula> matriculaOpt = this.matriculaRepository
                .findByMatriculaPK_AlunoAndMatriculaPK_Turma(alunoOpt.get(), turmaOpt.get());

        if (matriculaOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Matrícula não encontrada."));
        }

        this.matriculaRepository.delete(matriculaOpt.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{alunoId}/{turmaId}")
    public ResponseEntity<?> update(@PathVariable Integer alunoId, @PathVariable Integer turmaId,
                                    @RequestBody MatriculaPKDTO pkdto) {
        Optional<Aluno> alunoOpt = this.alunosRepository.findById(alunoId);
        Optional<Turma> turmaOpt = this.turmaRepository.findById(turmaId);

        if (alunoOpt.isEmpty() || turmaOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Aluno ou turma não encontrados."));
        }

        Optional<Matricula> matriculaOpt = this.matriculaRepository
                .findByMatriculaPK_AlunoAndMatriculaPK_Turma(alunoOpt.get(), turmaOpt.get());

        if (matriculaOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Matrícula não encontrada."));
        }

        Optional<Aluno> novoAlunoOpt = this.alunosRepository.findById(pkdto.aluno_id());
        Optional<Turma> novaTurmaOpt = this.turmaRepository.findById(pkdto.turma_id());

        if (novoAlunoOpt.isEmpty() || novaTurmaOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Novo aluno ou nova turma não encontrados."));
        }

        MatriculaPK novaMatriculaPK = new MatriculaPK(novoAlunoOpt.get(), novaTurmaOpt.get());
        Matricula matricula = matriculaOpt.get();
        matricula.setMatriculaPK(novaMatriculaPK);

        this.matriculaRepository.save(matricula);
        return ResponseEntity.ok(matricula);
    }
}
