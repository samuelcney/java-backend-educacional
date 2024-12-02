package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.dto.DisciplinaRequestDTO;
import br.grupointegrado.educacional.model.Curso;
import br.grupointegrado.educacional.model.Disciplina;
import br.grupointegrado.educacional.model.Professor;
import br.grupointegrado.educacional.repository.CursoRepository;
import br.grupointegrado.educacional.repository.DisciplinaRepository;
import br.grupointegrado.educacional.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll(){
        return ResponseEntity.ok(this.disciplinaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        Disciplina disciplina = this.disciplinaRepository.findById(id)
                .orElse(null);

        if(disciplina == null){
            ResponseEntity.status(404).body(Map.of("message", "Disciplina não encontrada"));
        }

        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DisciplinaRequestDTO dto){
        Professor professor = this.professorRepository.findById(dto.professor_id())
                .orElse(null);

        if(professor == null){
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Professor não encontrado"));
        }

        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElse(null);

        if(curso == null){
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Curso não encontrado"));
        }

        Disciplina disciplina = new Disciplina();

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        disciplina.setProfessor(professor);
        disciplina.setCurso(curso);

        this.disciplinaRepository.save(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody DisciplinaRequestDTO dto, @PathVariable Integer id){

        Disciplina disciplina = this.disciplinaRepository.findById(id).orElse(null);

        if(disciplina == null){
            ResponseEntity.status(404).body(Map.of("message", "Disciplina não encontrada"));
        }


        if(dto.professor_id() != null){
            Professor professor = this.professorRepository.findById(dto.professor_id())
                    .orElse(null);

            if(professor == null){
                return ResponseEntity.status(404)
                        .body(Map.of("message", "Professor não encontrado"));
            }

            disciplina.setProfessor(professor);
        }

        if(dto.curso_id() != null){
            Curso curso = this.cursoRepository.findById(dto.curso_id())
                    .orElse(null);

            if(curso == null){
                return ResponseEntity.status(404)
                        .body(Map.of("message", "Curso não encontrado"));
            }

            disciplina.setCurso(curso);
        }


        if (dto.nome() != null && !dto.nome().isBlank()) {
            disciplina.setNome(dto.nome());
        }

        if (dto.codigo() != null && !dto.codigo().isBlank()) {
            disciplina.setCodigo(dto.codigo());
        }

        this.disciplinaRepository.save(disciplina);
        return ResponseEntity.ok()
                .body(disciplina);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Integer id){

        if(!this.disciplinaRepository.existsById(id)){
            return ResponseEntity.status(404).body(Map.of("message", "Disciplina não encontrada"));
        }

        this.disciplinaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
