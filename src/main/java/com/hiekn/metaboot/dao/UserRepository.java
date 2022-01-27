package com.hiekn.metaboot.dao;

import cn.hiboot.mcn.autoconfigure.jpa.BaseRepository;
import com.hiekn.metaboot.bean.po.UserBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserBean, Integer> {

    UserBean findByUsername(String username);

}