package com.hiekn.metaboot.service;

import com.hiekn.metaboot.base.BaseService;
import com.hiekn.metaboot.bean.UserBean;

public interface UserService extends BaseService<UserBean,String> {
    UserBean getByMobile(String mobile);
    UserBean login(String mobile, String password);
    void logout();
}