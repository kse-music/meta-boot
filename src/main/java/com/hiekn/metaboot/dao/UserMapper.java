package com.hiekn.metaboot.dao;

import com.hiekn.metaboot.bean.po.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends JpaRepository<UserBean, String> {
}