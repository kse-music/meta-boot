package com.hiekn.metaboot.service.impl;

import com.hiekn.metaboot.bean.result.ErrorCodes;
import com.hiekn.metaboot.bean.vo.TokenModel;
import com.hiekn.metaboot.conf.Constants;
import com.hiekn.metaboot.exception.ServiceException;
import com.hiekn.metaboot.service.TokenManagerService;
import com.hiekn.metaboot.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Service
public class TokenManagerServiceImpl implements TokenManagerService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public TokenModel createToken (long userId) {
        // 使用 uuid 作为源 token
        String token = CommonUtils.getRandomUUID();
        TokenModel model = new TokenModel (userId, token);
        // 存储到 redis 并设置过期时间
        redisTemplate.boundValueOps (userId).set (token, Constants.tokenExpiresTimeDay, TimeUnit.DAYS);
        return model;
    }

    @Override
    public TokenModel getToken (TokenModel tokenModel) {
        String authentication = tokenModel.getAuthentication();
        if(authentication == null){
            throw ServiceException.newInstance(ErrorCodes.UN_LOGIN_ERROR);
        }
        if(authentication.length() < 33){
            throw ServiceException.newInstance(ErrorCodes.AUTHENTICATION_ERROR);
        }
        try {
            tokenModel.setUserId(Integer.valueOf(authentication.substring(32)));
            tokenModel.setToken(authentication.substring(0,32));
        } catch (Exception e) {
            throw ServiceException.newInstance(ErrorCodes.AUTHENTICATION_ERROR);
        }
        return tokenModel;
    }

    @Override
    public boolean checkToken (TokenModel tokenModel) {
        TokenModel model = getToken(tokenModel);
        Object token = redisTemplate.boundValueOps (model.getUserId ()).get();
        if (token == null || !token.equals (model.getToken ())) {
            return false;
        }
        // 如果验证成功，说明此用户进行了一次有效操作，延长 token 的过期时间
        redisTemplate.boundValueOps (model.getUserId ()).expire (Constants.tokenExpiresTimeDay, TimeUnit.DAYS);
        return true;
    }

    @Override
    public void deleteToken (long userId) {
        redisTemplate.delete (userId);
    }

}
