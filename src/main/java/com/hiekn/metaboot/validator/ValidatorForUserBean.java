package com.hiekn.metaboot.validator;

import cn.hiboot.mcn.autoconfigure.web.util.SpringBeanUtils;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Pattern;

public class ValidatorForUserBean implements ConstraintValidator<UserBeanValidator, UserBean> {

    public void initialize(UserBeanValidator constraintAnnotation) {
        constraintAnnotation.message();
    }

    public boolean isValid(UserBean value, ConstraintValidatorContext context) {
        if(Objects.isNull(value)){
            return false;
        }
        UserService userService = SpringBeanUtils.getBean(UserService.class);
        UserBean bean = userService.getByMobile(value.getMobile());
        if(Objects.nonNull(bean)){
            sendErrMsg(context,"手机号已存在");
            return false;
        }
        return true;
    }

    private void sendErrMsg(ConstraintValidatorContext context,String msg){
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
    }

}

