package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.dto.CursoRequestDTO;
import br.grupointegrado.educacional.dto.TurmaRequestDTO;
import br.grupointegrado.educacional.model.Curso;
import br.grupointegrado.educacional.model.Turma;
import br.grupointegrado.educacional.repository.CursoRepository;
import br.grupointegrado.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){

        return ResponseEntity.ok(this.turmaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        Turma turma = this.turmaRepository.findById(id)
                .orElse(null);

        if(turma == null){
            return ResponseEntity.status(404).body(Map.of("message", "Turma não encontrada"));
        }

        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TurmaRequestDTO dto){

        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElse(null);

        if(curso  == null){
            return ResponseEntity.status(404).body(Map.of("message", "Curso não encontrado"));
        }

        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso(curso);

        this.turmaRepository.save(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TurmaRequestDTO dto, @PathVariable Integer id){
        Turma turma = this.turmaRepository.findById(id)
                .orElse(null);

        if(turma == null){
            return ResponseEntity.status(404).body(Map.of("message", "Turma não encontrada"));
        }

        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElse(null);

        if(curso == null){
            return ResponseEntity.status(404).body(Map.of("message", "Curso não encontrado"));
        }

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso(curso);

        this.turmaRepository.save(turma);
        return ResponseEntity.ok(turma);
    }

    public ResponseEntity<?> delete(@PathVariable Integer id){
        if(!this.turmaRepository.existsById(id)){
            return ResponseEntity.status(404).body(Map.of("message", "Turma não encontrada"));
        }

        this.turmaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
