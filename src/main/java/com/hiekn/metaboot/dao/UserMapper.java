package com.hiekn.metaboot.dao;

import com.hiekn.boot.web.jersey.mapper.BaseMapper;
import com.hiekn.metaboot.bean.UserBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserBean> {

    UserBean selectByUsername(String username);
}
