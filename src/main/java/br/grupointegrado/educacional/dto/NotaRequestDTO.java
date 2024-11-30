package br.grupointegrado.educacional.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotaRequestDTO(
        Integer disciplina_id,
        Integer matricula_id,
        BigDecimal nota,
        LocalDate dataLancamento
) {
}
