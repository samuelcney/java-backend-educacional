package br.grupointegrado.educacional.dto;

import br.grupointegrado.educacional.model.Matricula;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public record AlunoRequestDTO(
        String nome,
        String email,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dataNascimento
) {

    public String matriculaUUID(){
        return UUID.randomUUID().toString();
    }
}
