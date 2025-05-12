package com.electro.electro_app.infraestructure.utils.Validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistsByCountryNameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByCountryName {

    String message() default "Ya existe en la base de datos. Escoja otro Username.";

    Class<?>[] groups() default {};

    Class <? extends Payload>[] payload() default {};
}
