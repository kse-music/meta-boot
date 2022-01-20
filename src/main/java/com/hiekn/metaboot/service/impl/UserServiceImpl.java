package com.hiekn.metaboot.service.impl;

import cn.hiboot.mcn.core.exception.ServiceException;
import cn.hiboot.mcn.core.util.McnUtils;
import com.hiekn.metaboot.bean.po.UserBean;
import com.hiekn.metaboot.dao.UserRepository;
import com.hiekn.metaboot.exception.ErrorCodes;
import com.hiekn.metaboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

    @Override
    public void beforeSave(UserBean data) {
        data.setId(McnUtils.simpleUUID());
    }

    @Override
    public UserBean getByMobile(String mobile) {
        log.debug("请使用logger.debug替代System.out.println()！！！");
        UserBean userBean = getRepository().findByMobile(mobile);
        if(Objects.isNull(userBean)){
            throw ServiceException.newInstance(ErrorCodes.USER_NOT_FOUND);
        }
        return userBean;
    }

}