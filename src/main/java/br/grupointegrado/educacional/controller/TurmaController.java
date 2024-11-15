package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.dto.CursoRequestDTO;
import br.grupointegrado.educacional.dto.TurmaRequestDTO;
import br.grupointegrado.educacional.model.Curso;
import br.grupointegrado.educacional.model.Turma;
import br.grupointegrado.educacional.repository.CursoRepository;
import br.grupointegrado.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){

        return ResponseEntity.ok(this.turmaRepository.findAll());
    }

    @GetMapping("/{id}")
    public Turma findById(@PathVariable Integer id){
        return this.turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

    }

    @PostMapping
    public Turma create(@RequestBody TurmaRequestDTO dto){

        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(()-> new IllegalArgumentException("Turma não encontrada"));

        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso(curso);


        return this.turmaRepository.save(turma);
    }

    @PutMapping("/{id}")
    public Turma update(@RequestBody TurmaRequestDTO dto, @PathVariable Integer id){
        Turma turma = this.turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrada"));

        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(()-> new IllegalArgumentException("Curso não encontrada"));


        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso(curso);

        return this.turmaRepository.save(turma);
    }
}
