package br.grupointegrado.educacional.dto;

import java.util.List;

public record MatriculaRequestDTO(
        MatriculaPKDTO matriculaPK,
        List<NotaDTO> notas
) {
}
