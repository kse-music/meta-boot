package com.hiekn.metaboot.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.hiekn.metaboot.conf.Constants;
import com.hiekn.metaboot.exception.ErrorCodes;
import com.hiekn.metaboot.exception.ServiceException;
import com.hiekn.metaboot.service.TokenManageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class TokenManageServiceImpl implements TokenManageService {

    @Override
    public String createToken(Integer userId) {
        //签发时间
        Date iaDate = new Date();

        //过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.DATE, Constants.tokenExpiresTimeDay);
        Date expireDate = nowTime.getTime();

        Map<String,Object> map = Maps.newHashMap();
        map.put("alg","HS256");
        map.put("typ","JWT");
        return JWT.create()
                .withHeader(map)
                .withClaim("userId",userId)
                .withExpiresAt(expireDate)
                .withIssuedAt(iaDate)
                .withIssuer("hiekn")
                .sign(getAlgorithm());
    }

    private Algorithm getAlgorithm() {
        try {
            return Algorithm.HMAC256(SECRET);
        } catch (UnsupportedEncodingException e) {
            throw ServiceException.newInstance(ErrorCodes.TOKEN_CREATE_ERROR);
        }
    }

    @Override
    public Map<String, Claim> checkToken(String token) {
        JWTVerifier verifier = JWT.require(getAlgorithm()).build();
        DecodedJWT jwt;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw ServiceException.newInstance(ErrorCodes.AUTHENTICATION_ERROR);
        }

        return jwt.getClaims();
    }

    @Override
    public Integer getCurrentUserId() {
        Map<String, Claim> claimMap = checkToken(getToken());
        return claimMap.get("userId").asInt();
    }

    @Override
    public String getToken() {
        String authorization = getAuthorization();
        return authorization.split(" ")[1];
    }

    private String getAuthorization(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        String authorization = request.getHeader("Authorization");
        if(StringUtils.isBlank(authorization)){
            throw ServiceException.newInstance(ErrorCodes.AUTHENTICATION_ERROR);
        }
        return authorization;
    }
}
