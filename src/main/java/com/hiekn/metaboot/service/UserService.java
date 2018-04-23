package com.hiekn.metaboot.service;


import com.hiekn.boot.autoconfigure.base.service.BaseService;
import com.hiekn.metaboot.bean.UserBean;

public interface UserService extends BaseService<UserBean,Integer> {
    UserBean getByUsername(String username);

    UserBean login(String username, String password);
    void logout();
}
