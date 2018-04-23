package com.hiekn.metaboot.service.impl;

import com.hiekn.boot.autoconfigure.base.exception.ServiceException;
import com.hiekn.boot.autoconfigure.base.service.BaseServiceImpl;
import com.hiekn.boot.autoconfigure.jwt.JwtToken;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.dao.UserMapper;
import com.hiekn.metaboot.exception.ErrorCodes;
import com.hiekn.metaboot.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserBean,Integer> implements UserService {

    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtToken jwtToken;

    @Override
    public UserBean getByUsername(String username) {
        logger.info("请使用logger替代System.out.println！！！");
        return userMapper.selectByUsername(username);
    }

    @Override
    public UserBean login(String username, String password) {
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw ServiceException.newInstance(ErrorCodes.PARAM_PARSE_ERROR);
        }
        UserBean user = getByUsername(username);
        if(Objects.isNull(user)){
            throw ServiceException.newInstance(ErrorCodes.USER_NOT_FOUND_ERROR);
        }
        if(!Objects.equals(DigestUtils.md5Hex(password), user.getPassword())){
            throw ServiceException.newInstance(ErrorCodes.USER_PWD_ERROR);
        }
        String token = jwtToken.createToken(user.getId());
        user.setToken(token);
        user.setPassword(null);
        return user;
    }


    @Override
    public void logout() {
        redisTemplate.delete (jwtToken.getUserIdAsInt());
    }

}
