package com.example.Aula1.validations;

import com.example.Aula1.Produtos.ReservaRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PeriodoValidoValidator implements ConstraintValidator<PeriodoValido, ReservaRequest> {

    @Override
    public boolean isValid(ReservaRequest value , ConstraintValidatorContext context) {
        if (value == null) return true;
        if (value.inicio() == null || value.fim() == null) return true;

        return value.inicio().isBefore(value.fim());
    }
}
