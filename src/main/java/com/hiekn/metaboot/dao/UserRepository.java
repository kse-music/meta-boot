package com.hiekn.metaboot.dao;

import cn.hiboot.mcn.autoconfigure.jpa.BaseRepository;
import com.hiekn.metaboot.bean.po.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Integer> {

    User findByUsername(String username);

}