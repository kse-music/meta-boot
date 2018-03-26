package com.hiekn.metaboot.service;

import com.hiekn.boot.web.jersey.result.RestData;
import com.hiekn.metaboot.bean.vo.PageModel;

import java.util.Map;

public interface BaseService<T> {
    T save(T pojo);
    void deleteByPrimaryKey(Object id);
    T getByPrimaryKey(Object id);
    void updateByPrimaryKeySelective(T pojo);
    RestData<T> listByPage(PageModel pageModel, Map<String, Object> params);

}
