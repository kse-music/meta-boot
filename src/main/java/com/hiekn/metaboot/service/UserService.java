package com.hiekn.metaboot.service;


import com.hiekn.boot.autoconfigure.base.service.BaseService;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.bean.vo.UserLoginBean;

public interface UserService extends BaseService<UserBean,Integer> {
    UserBean getByUsername(String username);

    UserLoginBean login(String username, String password);
    void logout();
}
