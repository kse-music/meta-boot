package com.hiekn.metaboot.bean.vo;

public class TokenModel {

    private long userId;

    private String token;

    private String authentication;

    public TokenModel(){}

    public TokenModel(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public TokenModel(long userId, String token, String authentication) {
        this.userId = userId;
        this.token = token;
        this.authentication = authentication;
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
