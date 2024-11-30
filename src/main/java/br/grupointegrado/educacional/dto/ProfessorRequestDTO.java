package br.grupointegrado.educacional.dto;

import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

public record ProfessorRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Email é obrigatório")
        String email,

        String telefone,

        String especialidade
) {
}
