package com.hiekn.metaboot.validator;

import com.hiekn.boot.autoconfigure.base.util.BeanUtils;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckUniqueMobileValidator implements ConstraintValidator<UniqueMobile, String> {

    public void initialize(UniqueMobile constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        UserService userService = BeanUtils.getBean(UserService.class);
        UserBean userBean = new UserBean();
        userBean.setMobile(value);
        UserBean bean = userService.selectByCondition(userBean);
        return bean == null;
    }
}

