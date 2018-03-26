package com.hiekn.metaboot.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaseMapper<T> {
    int insert(T pojo);
    int insertSelective(T record);
    int deleteByPrimaryKey(Object id);
    T selectByPrimaryKey(Object id);
    int updateByPrimaryKeySelective(T pojo);
    List<T> pageSelect(Map<String, Object> params);
    int pageCount(Map<String, Object> params);

}
