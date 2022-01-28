package com.hiekn.metaboot.service;

import cn.hiboot.mcn.autoconfigure.jpa.BaseService;
import com.hiekn.metaboot.bean.po.User;
import com.hiekn.metaboot.dao.UserRepository;

public interface UserService extends BaseService<User,Integer, UserRepository> {
    User getByMobile(String mobile);
}