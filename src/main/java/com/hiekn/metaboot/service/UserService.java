package com.hiekn.metaboot.service;

import com.hiekn.boot.web.jersey.service.BaseService;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.bean.vo.UserLoginBean;

public interface UserService extends BaseService<UserBean> {
    UserBean getByUsername(String username);

    UserLoginBean login(String username, String password);
    void logout();
}
