package br.grupointegrado.educacional.dto;

import br.grupointegrado.educacional.model.Matricula;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record AlunoRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        String email,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dataNascimento
) {

    public String matriculaUUID(){
        return UUID.randomUUID().toString().substring(0, 20);
    }
}
