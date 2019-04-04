package com.hiekn.metaboot.service.impl;

import com.hiekn.boot.autoconfigure.base.exception.RestException;
import com.hiekn.boot.autoconfigure.base.exception.ServiceException;
import com.hiekn.boot.autoconfigure.base.service.BaseServiceImpl;
import com.hiekn.boot.autoconfigure.jwt.JwtToken;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.dao.UserMapper;
import com.hiekn.metaboot.exception.ErrorCodes;
import com.hiekn.metaboot.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserBean,String> implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtToken jwtToken;

    /**
     * 修改、删除、详情，需把资源的主ID作为查询参数
     * 此处校验查出的数据是否为自己的，省去了sql中带userId查询
     */
    @Override
    public void assertSelfData(String id) {
        UserBean userBean = getByPrimaryKey(id);
        if(Objects.nonNull(userBean) && !userBean.getId().equals(jwtToken.getUserIdAsString())){
            throw RestException.newInstance(ErrorCodes.UNKNOWN_ERROR);
        }
    }

    @Override
    public UserBean getByMobile(String mobile) {
        logger.debug("请使用logger.debug替代System.out.println()！！！");
        UserBean userBean = new UserBean();
        userBean.setMobile(mobile);
        List<UserBean> list = selectByCondition(userBean);
        return list != null && !list.isEmpty() ? list.get(0):null;
    }

    @Override
    public UserBean login(String mobile, String password) {
        UserBean user = getByMobile(mobile);
        if(Objects.isNull(user)){
            throw ServiceException.newInstance(ErrorCodes.NOT_FOUND_ERROR);
        }
        if(!Objects.equals(DigestUtils.md5Hex(password), user.getPassword())){
            throw ServiceException.newInstance(ErrorCodes.USER_PWD_ERROR);
        }
        jwtToken.createToken(user.getId());
        user.setPassword(null);
        return user;
    }


    @Override
    public void logout() {
        redisTemplate.delete(jwtToken.getUserIdAsString());
    }

}