package com.hiekn.metaboot.service.impl;

import com.google.common.collect.Maps;
import com.hiekn.boot.web.jersey.result.RestData;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.bean.vo.PageModel;
import com.hiekn.metaboot.bean.vo.UserLoginBean;
import com.hiekn.metaboot.dao.UserMapper;
import com.hiekn.metaboot.exception.ErrorCodes;
import com.hiekn.metaboot.exception.ServiceException;
import com.hiekn.metaboot.service.TokenManageService;
import com.hiekn.metaboot.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserBean> implements UserService {

    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TokenManageService tokenManageService;

//    @Override
//    public RestData<UserBean> listByPage(PageModel pageModel, Map<String,Object> params) {
//        if(Objects.isNull(params)){
//            params = Maps.newHashMap();
//        }
//        params.put("pageNo",pageModel.getPageNo());
//        params.put("pageSize",pageModel.getPageSize());
//        List<UserBean> userList = userMapper.pageSelect(params);
//        logger.info("请使用logger替代System.out.println！！！");
//        return new RestData<>(userList,userMapper.pageCount(params));
//    }
//
//    @Override
//    public UserBean getByPrimaryKey(Object id) {
//        return userMapper.selectByPrimaryKey(id);
//    }

    @Override
    public UserBean getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

//    @Override
//    public List<UserBean> list() {
//        return userMapper.list();
//    }
//
//    @Override
//    public UserBean save(UserBean userBean) {
//        UserBean user = getByUsername(userBean.getUsername());
//        if(Objects.nonNull(user)){
//            throw ServiceException.newInstance(ErrorCodes.USER_EXIST_ERROR);
//        }
//        userMapper.insert(userBean);
//        return userBean;
//    }
//
//    @Override
//    public void deleteByPrimaryKey(Object id) {
//        userMapper.deleteByPrimaryKey(id);
//    }


    @Override
    public UserLoginBean login(String username, String password) {
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
        UserLoginBean userLoginBean = new UserLoginBean();
        BeanUtils.copyProperties(user,userLoginBean);
        String token = tokenManageService.createToken(user.getId());
        userLoginBean.setToken(token);
        userLoginBean.setPassword(null);
        return userLoginBean;
    }


    @Override
    public void logout() {
        redisTemplate.delete (tokenManageService.getCurrentUserId());
    }

}
