package com.hiekn.metaboot.validator;

import cn.hiboot.mcn.autoconfigure.validator.ConstraintValidatorExtend;
import com.hiekn.metaboot.bean.param.UserParam;
import com.hiekn.metaboot.bean.po.UserBean;
import com.hiekn.metaboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ValidatorForUser implements ConstraintValidatorExtend<UserValidator, UserParam> {

    @Autowired
    private UserService userService;

    private void config(ConstraintValidatorContext context,String msg) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
    }

    @Override
    public boolean isValid(UserParam value,ConstraintValidatorContext context) {
        if(Objects.isNull(value)){
            return false;
        }
        UserBean bean = userService.getRepository().findByUsername(value.getUsername());
        if(Objects.isNull(bean)){
            return true;
        }
        config(context,"手机号已存在");
        return false;
    }

}

