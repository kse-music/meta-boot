package com.hiekn.metaboot.bean.vo;

import javax.ws.rs.HeaderParam;

/**
 * 约定前台传入的验证为 t+u
 */
public class TokenModel {

    private long userId;

    private String token;

    @HeaderParam("Authentication")
    private String authentication;

    public TokenModel(){}

    public TokenModel(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

}
