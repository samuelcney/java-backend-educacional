package br.grupointegrado.educacional.dto;

import br.grupointegrado.educacional.model.Curso;

public record TurmaRequestDTO(int ano, int semestre, Integer curso_id) {
}
