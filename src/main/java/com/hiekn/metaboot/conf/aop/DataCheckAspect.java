package com.hiekn.metaboot.conf.aop;

import com.hiekn.boot.autoconfigure.base.exception.RestException;
import com.hiekn.boot.autoconfigure.jwt.JwtToken;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.exception.ErrorCodes;
import com.hiekn.metaboot.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 修改、删除、详情，需把资源的主ID作为第一个参数
 * 此处校验查出的数据是否为自己的，省去了sql中带userId查询
 */
@Aspect
@Component
public class DataCheckAspect {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtToken jwtToken;

    @Around("execution(* com.hiekn.metaboot.rest.*.*(..)) && @annotation(com.hiekn.metaboot.conf.aop.CheckData)")
    public Object log(ProceedingJoinPoint p) throws Throwable{
        Object[] args = p.getArgs();
        UserBean userBean = userService.getByPrimaryKey((String) args[0]);
        if(Objects.nonNull(userBean) && !userBean.getId().equals(jwtToken.getUserIdAsString())){
            throw RestException.newInstance(ErrorCodes.UNKNOWN_ERROR);
        }
        return p.proceed();
    }

}
