package com.hiekn.metaboot.validator;

import com.hiekn.metaboot.bean.param.UserParam;
import com.hiekn.metaboot.bean.po.User;
import com.hiekn.metaboot.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class ValidatorForUser implements ConstraintValidator<UserValidator, UserParam> {

    @Autowired
    private UserService userService;

    private void config(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("手机号已存在").addConstraintViolation();
    }

    @Override
    public boolean isValid(UserParam value,ConstraintValidatorContext context) {
        if(Objects.isNull(value)){
            return false;
        }
        User bean = userService.getRepository().findByUsername(value.getUsername());
        if(Objects.isNull(bean)){
            return true;
        }
        config(context);
        return false;
    }

}

