package com.hiekn.metaboot.conf;

import com.hiekn.metaboot.exception.ErrorCodes;
import com.hiekn.metaboot.bean.vo.TokenModel;
import com.hiekn.metaboot.exception.ServiceException;
import com.hiekn.metaboot.service.TokenManagerService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Aspect
@Configuration
public class LoginPermission {

    @Value("#{'${exclude_method}'.split(',')}")
    private List<String> excludeMethod;

    @Autowired
    private TokenManagerService tokenManagerService;


    @Around("@within(org.springframework.stereotype.Controller)")
    public Object checkLogin(ProceedingJoinPoint pjp) throws Throwable{
        String name = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        if(!excludeMethod.contains(name)){
            Object tModel = args[0];
            if(tModel instanceof TokenModel){
                TokenModel tokenModel = (TokenModel)tModel;
                if(!tokenManagerService.checkToken(tokenModel)){
                    throw ServiceException.newInstance(ErrorCodes.NO_MATCH_ERROR);
                }
            }

        }
        return pjp.proceed(args);
    }

}
