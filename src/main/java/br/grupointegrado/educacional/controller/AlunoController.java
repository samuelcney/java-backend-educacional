package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.dto.AlunoRequestDTO;
import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.repository.AlunosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public Aluno findById(@PathVariable Integer id){
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

    }

    @PostMapping
    public Aluno create(@RequestBody AlunoRequestDTO dto){
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matriculaUUID());
        aluno.setDataNascimento(dto.dataNascimento());

        return this.repository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@RequestBody AlunoRequestDTO dto, @PathVariable Integer id){
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));   
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(aluno.getMatricula());
        aluno.setDataNascimento(dto.dataNascimento());

        return this.repository.save(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        this.repository.delete(aluno);
        return ResponseEntity.noContent().build();
    }
}
