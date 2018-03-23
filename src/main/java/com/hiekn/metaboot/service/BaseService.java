package com.hiekn.metaboot.service;

import com.hiekn.boot.web.jersey.result.RestData;
import com.hiekn.metaboot.bean.vo.PageModel;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
    /**
     *
     * 增加一个实体
     * @param pojo 实体对象
     * @return 实体对象
     */
    T save(T pojo);

    /**
     *
     * 通过id删除实体
     *
     * @param id 主键id
     */
    void deleteByPrimaryKey(Object id);

    /**
     *
     * 通过主键获取实体
     *
     * @param id 主键id
     * @return 单个对象
     */
    T getByPrimaryKey(Object id);

    /**
     *
     * 查询所有实体
     *
     * @return 所有数据
     */
    List<T> list();

    /**
     *
     * 分页查询
     *
     * @param pageModel 分页参数
     * @param params 查询条件
     * @return 符合条件的对象
     */
    RestData<T> listByPage(PageModel pageModel, Map<String,Object> params);

}
