package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.dto.NotaRequestDTO;
import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.model.Disciplina;
import br.grupointegrado.educacional.model.Matricula;
import br.grupointegrado.educacional.model.Nota;
import br.grupointegrado.educacional.repository.DisciplinaRepository;
import br.grupointegrado.educacional.repository.MatriculaRepository;
import br.grupointegrado.educacional.repository.NotaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping
    public ResponseEntity<List<Nota>> findAll() {
        return ResponseEntity.ok(this.notaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Nota nota = this.notaRepository.findById(id)
                .orElse(null);

        if(nota == null){
            return ResponseEntity.status(404).body(Map.of("message", "Nota não encontrada"));
        }

        return ResponseEntity.ok(nota);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NotaRequestDTO dto) {
        Disciplina disciplina = this.disciplinaRepository.findById(dto.disciplina_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Disciplina inválida"));

        Matricula matricula = this.matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matrícula inválida"));

        Nota nota = new Nota();
        nota.setDisciplina(disciplina);
        nota.setMatricula(matricula);
        nota.setNota(dto.nota());
        nota.setDataLancamento(dto.dataLancamento() != null ? dto.dataLancamento() : LocalDate.now());

        Nota savedNota = this.notaRepository.save(nota);

        return ResponseEntity.created(URI.create("/api/notas/" + savedNota.getId()))
                .body(savedNota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid NotaRequestDTO dto, @PathVariable Integer id) {
        Nota nota = this.notaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota não encontrada"));

        if (dto.disciplina_id() != null) {
            nota.setDisciplina(this.disciplinaRepository.findById(dto.disciplina_id())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Disciplina inválida")));
        }

        if (dto.matricula_id() != null) {
            nota.setMatricula(this.matriculaRepository.findById(dto.matricula_id())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matrícula inválida")));
        }

        if (dto.nota() != null) {
            nota.setNota(dto.nota());
        }

        if (dto.dataLancamento() != null) {
            nota.setDataLancamento(dto.dataLancamento());
        }

        return ResponseEntity.ok(this.notaRepository.save(nota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (!this.notaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Nota não encontrada"));
        }

        this.notaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
