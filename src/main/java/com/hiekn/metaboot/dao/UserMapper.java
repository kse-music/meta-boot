package com.hiekn.metaboot.dao;

import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.bean.vo.PageModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<UserBean> getUserList(PageModel pageModel);
    int count();
    UserBean selectById(Integer id);
    void insert(UserBean userBean);
    UserBean selectByUsername(String username);
}
