package com.hiekn.metaboot.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatorForUser.class)
public @interface UserValidator {

    String message() default "存在不合法的参数";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
