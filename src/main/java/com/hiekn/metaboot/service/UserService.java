package com.hiekn.metaboot.service;

import cn.hiboot.mcn.autoconfigure.jpa.JpaService;
import com.hiekn.metaboot.bean.po.UserBean;
import com.hiekn.metaboot.dao.UserRepository;

public interface UserService extends JpaService<UserBean,Integer, UserRepository> {
    UserBean getByMobile(String mobile);
}