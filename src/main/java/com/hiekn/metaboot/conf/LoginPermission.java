package com.hiekn.metaboot.conf;

import com.hiekn.boot.autoconfigure.base.exception.ServiceException;
import com.hiekn.boot.autoconfigure.jwt.JwtToken;
import com.hiekn.metaboot.exception.ErrorCodes;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class LoginPermission {

    @Value("#{'${exclude_method}'.split(',')}")
    private List<String> excludeMethod;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtToken jwtToken;


    @Around("@within(org.springframework.stereotype.Controller)")
    public void checkLogin(ProceedingJoinPoint pjp) throws Throwable{
        String name = pjp.getSignature().getName();
        if(!excludeMethod.contains(name)){
            String token = jwtToken.getToken();
            try {
                jwtToken.checkToken(token);
            } catch (Exception e) {
                throw ServiceException.newInstance(ErrorCodes.AUTHENTICATION_ERROR);
            }
//            Integer userId = tokenManageService.getCurrentUserId();
//            Object token2 = redisTemplate.boundValueOps(userId).get();
//            if (token2 == null || !token2.equals (token)) {
//                throw ServiceException.newInstance(ErrorCodes.AUTHENTICATION_ERROR);
//            }
        }
        pjp.proceed();//execute advice method
    }

}
