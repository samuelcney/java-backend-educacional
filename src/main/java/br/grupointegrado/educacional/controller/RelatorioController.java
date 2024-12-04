package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.model.Disciplina;
import br.grupointegrado.educacional.model.Nota;
import br.grupointegrado.educacional.repository.AlunosRepository;
import br.grupointegrado.educacional.repository.DisciplinaRepository;
import br.grupointegrado.educacional.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AlunosRepository alunosRepository;

    @GetMapping("/boletim/aluno/{alunoId}")
    public ResponseEntity<?> boletimPorAluno(@PathVariable Integer alunoId) {

        Aluno aluno = this.alunosRepository.findById(alunoId).orElse(null);

        if(aluno == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Aluno não encontrado"));
        }

        List<Nota> notas = notaRepository.findByMatricula_MatriculaPK_Aluno_Id(alunoId);
        if (notas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        BigDecimal media = notas.stream()
                .map(Nota::getNota)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(notas.size()), 2, RoundingMode.HALF_UP);

        Map<String, Object> resultado = Map.of(
                "alunoId", aluno.getNome(),
                "media", media,
                "notas", notas.stream()
                        .map(n -> Map.of(
                                "disciplina", n.getDisciplina().getNome(),
                                "nota", n.getNota(),
                                "dataLancamento", n.getDataLancamento()
                        )).collect(Collectors.toList())
        );

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/desempenho/turma/{turmaId}")
    public ResponseEntity<?> desempenhoPorTurma(@PathVariable Integer turmaId) {
        List<Nota> notas = notaRepository.findByMatricula_MatriculaPK_Turma_Id(turmaId);
        if (notas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> resultado = Map.of(
                "turmaId", turmaId,
                "mediaGeral", notas.stream()
                        .map(Nota::getNota)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(new BigDecimal(notas.size()), 2, RoundingMode.HALF_UP),
                "alunos", notas.stream()
                        .collect(Collectors.groupingBy(
                                n -> n.getMatricula().getMatriculaPK().getAluno().getNome(),
                                Collectors.averagingDouble(n -> n.getNota().doubleValue())
                        )).entrySet().stream()
                        .map(entry -> Map.of(
                                "aluno", entry.getKey(),
                                "media", BigDecimal.valueOf(entry.getValue()).setScale(2, RoundingMode.HALF_UP)
                        )).collect(Collectors.toList())
        );

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/desempenho/disciplina/{disciplinaId}")
    public ResponseEntity<?> desempenhoPorDisciplina(@PathVariable Integer disciplinaId) {

        Disciplina disciplina = this.disciplinaRepository.findById(disciplinaId).orElse(null);

        if(disciplina == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Disciplina não encontrada"));
        }

        List<Nota> notas = notaRepository.findByDisciplina_Id(disciplinaId);
        if (notas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> resultado = Map.of(
                "disciplina", disciplina.getNome(),
                "mediaGeral", notas.stream()
                        .map(Nota::getNota)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(new BigDecimal(notas.size()), 2, RoundingMode.HALF_UP),
                "alunos", notas.stream()
                        .map(n -> Map.of(
                                "aluno", n.getMatricula().getMatriculaPK().getAluno().getNome(),
                                "nota", n.getNota()
                        )).collect(Collectors.toList())
        );

        return ResponseEntity.ok(resultado);
    }
}
