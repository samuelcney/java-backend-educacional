package br.grupointegrado.educacional.dto;

import java.sql.Date;

public record ProfessorRequestDTO(String nome, String email, String telefone, String especialidade) {
}
