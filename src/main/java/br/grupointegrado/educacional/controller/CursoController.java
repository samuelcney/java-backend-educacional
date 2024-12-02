package br.grupointegrado.educacional.controller;

import br.grupointegrado.educacional.dto.CursoRequestDTO;
import br.grupointegrado.educacional.model.Curso;
import br.grupointegrado.educacional.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(){

        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        Curso curso = this.repository.findById(id)
                .orElse(null);

        if(curso == null){
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Curso não encontrado"));
        }

        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> create(@RequestBody CursoRequestDTO dto){
        Curso curso = new Curso();

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());


        return ResponseEntity.status(HttpStatus.CREATED).body(this.repository.save(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CursoRequestDTO dto, @PathVariable Integer id){
        Curso curso = this.repository.findById(id)
                .orElse(null);

        if(curso == null){
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Curso não encontrado"));
        }

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        return ResponseEntity.ok(this.repository.save(curso));
    }

    public ResponseEntity<?> delete(@PathVariable Integer id){

        if(!this.repository.existsById(id)) {
            return ResponseEntity.status(404)
                    .body(Map.of("message", "Curso não encontrado"));
        }

        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
