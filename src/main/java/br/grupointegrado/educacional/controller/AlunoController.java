package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.dto.AlunoRequestDTO;
import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.repository.AlunosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunosRepository repository;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(){

        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        Aluno aluno = this.repository.findById(id)
                .orElse(null);

        if(aluno == null){
            return ResponseEntity.status(404).body(Map.of("message", "Aluno não encontrado"));
        }

        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody AlunoRequestDTO dto){
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matriculaUUID());
        aluno.setDataNascimento(dto.dataNascimento());;

        return ResponseEntity.status(HttpStatus.CREATED).body(this.repository.save(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody AlunoRequestDTO dto, @PathVariable Integer id){
        Aluno aluno = this.repository.findById(id)
                .orElse(null);

        if(aluno == null){
            return ResponseEntity.status(404).body(Map.of("message", "Aluno não encontrado"));
        }

        if (dto.nome() != null) {
            aluno.setNome(dto.nome());
        }
        if (dto.email() != null) {
            aluno.setEmail(dto.email());
        }
        if (dto.dataNascimento() != null) {
            aluno.setDataNascimento(dto.dataNascimento());
        }

        return ResponseEntity.ok(this.repository.save(aluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        if(!this.repository.existsById(id)){
            return ResponseEntity.status(404).body(Map.of("message", "Aluno não encontrado"));
        }

        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
