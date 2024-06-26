package com.javaproject.customer_springboot.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ContactValidator.class)
@Target( { METHOD, FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactValidation {
    String message() default "Dados do contato do cliente invalido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
