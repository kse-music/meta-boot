package com.hiekn.metaboot.service.impl;

import com.google.common.collect.Maps;
import com.hiekn.boot.web.jersey.result.RestData;
import com.hiekn.metaboot.bean.vo.PageModel;
import com.hiekn.metaboot.dao.BaseMapper;
import com.hiekn.metaboot.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public T save(T pojo) {
        baseMapper.insert(pojo);
        return pojo;
    }

    @Override
    public void deleteByPrimaryKey(Object id) {
        baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public T getByPrimaryKey(Object id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> list() {
        return baseMapper.list();
    }

    @Override
    public RestData<T> listByPage(PageModel pageModel, Map<String, Object> params) {
        if(Objects.isNull(params)){
            params = Maps.newHashMap();
        }
        params.put("pageNo",pageModel.getPageNo());
        params.put("pageSize",pageModel.getPageSize());
        return new RestData<>(baseMapper.pageSelect(params),baseMapper.pageCount(params));
    }
}
