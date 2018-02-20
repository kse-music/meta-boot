package com.hiekn.metaboot.dao;

import com.hiekn.metaboot.bean.UserBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends CommonMapper<UserBean>{

    UserBean selectByUsername(String username);
}
