package br.grupointegrado.educacional.dto;

import java.util.List;

public record TurmaRequestDTO(
        int ano,
        int semestre,
        Integer curso_id
) {
}
