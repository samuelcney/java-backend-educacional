package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.model.Disciplina;
import br.grupointegrado.educacional.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll(){
        return ResponseEntity.ok(this.disciplinaRepository.findAll());
    }

    @GetMapping("/{id}")
    public Disciplina findById(@PathVariable Integer id){
        return this.disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada."));
    }

    public Disciplina create(@RequestBody )
}
