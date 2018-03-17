package com.hiekn.metaboot.conf;

import com.hiekn.metaboot.exception.ErrorCodes;
import com.hiekn.metaboot.exception.ServiceException;
import com.hiekn.metaboot.util.JwtToken;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Aspect
@Configuration
public class LoginPermission {

    @Value("#{'${exclude_method}'.split(',')}")
    private List<String> excludeMethod;

    @Autowired
    private RedisTemplate redisTemplate;


    @Around("@within(org.springframework.stereotype.Controller)")
    public Object checkLogin(ProceedingJoinPoint pjp) throws Throwable{
        String name = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        if(!excludeMethod.contains(name)){
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest req = sra.getRequest();
            String authorization = req.getHeader("Authorization");
            String token = JwtToken.getToken(authorization);
            if(Objects.isNull(token)){
                throw ServiceException.newInstance(ErrorCodes.AUTHENTICATION_ERROR);
            }
            Integer userId = JwtToken.checkToken(token);
            Object token2 = redisTemplate.boundValueOps (userId).get();
            if (token2 == null || !token2.equals (token)) {
                throw ServiceException.newInstance(ErrorCodes.AUTHENTICATION_ERROR);
            }
        }
        return pjp.proceed(args);
    }

}
