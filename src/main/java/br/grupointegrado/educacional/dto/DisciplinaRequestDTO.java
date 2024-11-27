package br.grupointegrado.educacional.dto;

import java.util.List;

public record DisciplinaRequestDTO(
        String nome,
        String codigo,
        Integer curso_id,
        Integer professor_id,
        List<NotaDTO> notas
){
}
