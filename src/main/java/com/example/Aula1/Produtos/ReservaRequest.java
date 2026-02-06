package com.example.Aula1.Produtos;

import com.example.Aula1.validations.PeriodoValido;

import java.time.LocalDate;

@PeriodoValido
public record ReservaRequest(
        LocalDate inicio , LocalDate fim
) {
}
