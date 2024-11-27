package br.grupointegrado.educacional.dto;

import java.math.BigDecimal;
import java.util.Date;

public record NotaDTO(
        Integer disciplina_id,
        Integer matricula_id,
        BigDecimal nota,
        Date dataLancamento
) {
}
