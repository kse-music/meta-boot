package com.hiekn.metaboot.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

public interface TokenManageService {
    String SECRET = "SECRET";
    String createToken(Integer userId);
    Map<String, Claim> checkToken(String token);
    Integer getCurrentUserId();
    String getToken();


}
