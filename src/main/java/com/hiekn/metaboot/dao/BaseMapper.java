package com.hiekn.metaboot.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaseMapper<T> {
    /**
     *
     * 增加一个实体
     * @param pojo 实体对象
     * @return 影响的行数 0失败，1成功
     */
    int insert(T pojo);

    /**
     *
     * 通过id删除实体
     *
     * @param id 主键id
     * @return 影响的行数
     */
    int deleteByPrimaryKey(Object id);

    /**
     *
     * 通过主键获取实体
     *
     * @param id 主键id
     * @return 单个对象
     */
    T selectByPrimaryKey(Object id);

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
     * @param params 查询条件
     * @return 符合条件的对象
     */
    List<T> pageSelect(Map<String,Object> params);


    /**
     *
     * 分页查询时，用来统计总条数
     *
     * @param params 查询条件
     * @return 符合条件的结果总数
     */
    int pageCount(Map<String,Object> params);

}
