package com.hiekn.metaboot.service;

import cn.hiboot.mcn.core.service.JpaService;
import com.hiekn.metaboot.bean.po.UserBean;

public interface UserService extends JpaService<UserBean,String> {
    UserBean getByMobile(String mobile);
    UserBean login(String mobile, String password);
    void logout();
}