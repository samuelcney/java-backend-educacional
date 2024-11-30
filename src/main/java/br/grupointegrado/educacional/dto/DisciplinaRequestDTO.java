package br.grupointegrado.educacional.dto;

import br.grupointegrado.educacional.model.Nota;

import java.util.List;

public record DisciplinaRequestDTO(
        String nome,
        String codigo,
        Integer curso_id,
        Integer professor_id,
        List<Nota> notas
){
}
