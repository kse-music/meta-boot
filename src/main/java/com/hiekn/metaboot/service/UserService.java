package com.hiekn.metaboot.service;

import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.bean.vo.TokenModel;
import com.hiekn.metaboot.bean.vo.UserLoginBean;

public interface UserService extends CommonService<UserBean> {
    UserBean getByUsername(String username);

    UserLoginBean login(String username, String password);
    void logout(TokenModel tokenModel);
}
