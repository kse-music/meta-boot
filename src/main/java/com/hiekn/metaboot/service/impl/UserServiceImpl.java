package com.hiekn.metaboot.service.impl;

import cn.hiboot.mcn.core.exception.ServiceException;
import cn.hiboot.mcn.core.util.McnUtils;
import com.hiekn.metaboot.bean.po.UserBean;
import com.hiekn.metaboot.dao.UserMapper;
import com.hiekn.metaboot.exception.ErrorCodes;
import com.hiekn.metaboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public JpaRepository<UserBean, String> getRepository() {
        return userMapper;
    }

    @Override
    public void beforeSave(UserBean data) {
        data.setId(McnUtils.simpleUUID());
    }

    @Override
    public UserBean getByMobile(String mobile) {
        log.debug("请使用logger.debug替代System.out.println()！！！");
        UserBean userBean = new UserBean();
        userBean.setMobile(mobile);
        return userMapper.findOne(Example.of(userBean)).orElse(null);
    }

    @Override
    public UserBean login(String mobile, String password) {
        UserBean user = getByMobile(mobile);
        if(Objects.isNull(user)){
            throw ServiceException.newInstance(ErrorCodes.NOT_FOUND_ERROR);
        }
        if(!Objects.equals(new String(DigestUtils.md5Digest(password.getBytes())), user.getPassword())){
            throw ServiceException.newInstance(ErrorCodes.USER_PWD_ERROR);
        }
        user.setPassword(null);
        return user;
    }


    @Override
    public void logout() {

    }

}