package com.hiekn.metaboot.conf;

import com.hiekn.metaboot.exception.ErrorCodes;
import com.hiekn.metaboot.exception.ServiceException;
import com.hiekn.metaboot.service.TokenManageService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Aspect
@Configuration
public class LoginPermission {

    @Value("#{'${exclude_method}'.split(',')}")
    private List<String> excludeMethod;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TokenManageService tokenManageService;


    @Around("@within(org.springframework.stereotype.Controller)")
    public Object checkLogin(ProceedingJoinPoint pjp) throws Throwable{
        String name = pjp.getSignature().getName();
        if(!excludeMethod.contains(name)){
            String token = tokenManageService.getToken();
            tokenManageService.checkToken(token);
//            Integer userId = tokenManageService.getCurrentUserId();
//            Object token2 = redisTemplate.boundValueOps(userId).get();
//            if (token2 == null || !token2.equals (token)) {
//                throw ServiceException.newInstance(ErrorCodes.AUTHENTICATION_ERROR);
//            }
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            String newToken = tokenManageService.createNewToken(token);
            if(newToken != null){
                response.setHeader("Authorization",newToken);
            }
        }
        return pjp.proceed();
    }

}
