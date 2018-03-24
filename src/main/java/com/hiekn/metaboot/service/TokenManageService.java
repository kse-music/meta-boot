package com.hiekn.metaboot.service;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface TokenManageService {
    String SECRET = "SECRET";
    String createToken(Integer userId);
    String createNewToken(String token);
    DecodedJWT checkToken(String token);
    Integer getCurrentUserId();
    String getToken();
}
