package br.grupointegrado.educacional.dto;

import java.sql.Date;
import java.util.UUID;

public record AlunoRequestDTO(
        String nome,
        String email,
        Date dataNascimento
) {

    public String matriculaUUID(){
        return UUID.randomUUID().toString();
    }
}
