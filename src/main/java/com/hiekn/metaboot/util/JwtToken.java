package com.hiekn.metaboot.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.hiekn.metaboot.conf.Constants;
import com.hiekn.metaboot.exception.ErrorCodes;
import com.hiekn.metaboot.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtToken {

    private final static String SECRET = "SECRET";

    public static String createToken(Integer userId){
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

    private static Algorithm getAlgorithm(){
        try {
            return Algorithm.HMAC256(SECRET);
        } catch (UnsupportedEncodingException e) {
            throw ServiceException.newInstance(ErrorCodes.TOKEN_CREATE_ERROR);
        }
    }

    public static Integer checkToken(String token){
        JWTVerifier verifier = JWT.require(getAlgorithm()).build();
        DecodedJWT jwt;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw ServiceException.newInstance(ErrorCodes.AUTHENTICATION_ERROR);
        }

        return jwt.getClaims().get("userId").asInt();
    }

    public static String getToken(String authorization){
        if(StringUtils.isBlank(authorization)){
            return null;
        }
        return authorization.split(" ")[1];
    }

}
