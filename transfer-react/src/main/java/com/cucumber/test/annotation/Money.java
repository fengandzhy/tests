package com.cucumber.test.annotation;


import com.cucumber.test.validators.MoneyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation is used to mark which fields require the corresponding validation. 
 * The specific validation logic is implemented in the MoneyValidator class.
 * */
@Documented
@Constraint(validatedBy = MoneyValidator.class)
@Target( { METHOD, FIELD, TYPE })
@Retention(RUNTIME)
public @interface Money {
    
    String message() default "please input correct money";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
