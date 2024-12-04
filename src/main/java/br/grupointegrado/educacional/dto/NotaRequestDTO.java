package br.grupointegrado.educacional.dto;

import br.grupointegrado.educacional.model.MatriculaPK;
import br.grupointegrado.educacional.model.Nota;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotaRequestDTO(
        @NotNull Integer disciplina_id,
        @NotNull Integer matricula_id,
        @NotNull @DecimalMin("0.0") @DecimalMax("10.0") BigDecimal nota,
        LocalDate dataLancamento
) {
}