package com.nhs.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValidRegularAmountValidator.class })
@Documented
public @interface ValidRegularAmount {
    String message() default
    "ValidRegularAmount Regular Amount invalid";
    
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}