package com.example.Aula1.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PeriodoValidoValidator.class)
@Target(ElementType.TYPE) // valida a CLASSE inteira
@Retention(RetentionPolicy.RUNTIME)
public @interface PeriodoValido {

    String message() default "Período inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
