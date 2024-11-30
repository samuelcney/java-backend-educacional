package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.dto.ProfessorRequestDTO;
import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.model.Professor;
import br.grupointegrado.educacional.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @GetMapping
    public ResponseEntity<List<Professor>> findAll(){

        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        Professor professor = this.repository.findById(id)
                .orElse(null);

        if(professor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aluno não encontrado!");
        }

        return ResponseEntity.ok(professor);

    }

    @PostMapping
    public Professor create(@RequestBody ProfessorRequestDTO dto){
        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return this.repository.save(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ProfessorRequestDTO dto, @PathVariable Integer id){
        Professor professor = this.repository.findById(id)
                .orElse(null);

        if(professor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aluno não encontrado!");
        }
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return ResponseEntity.ok(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        this.repository.delete(professor);
        return ResponseEntity.noContent().build();
    }
}
