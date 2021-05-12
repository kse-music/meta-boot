package com.hiekn.metaboot.base;

import cn.hiboot.mcn.core.model.result.RestResp;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 单表service通用接口
 *
 * @author DingHao
 * @since 2021/2/23 22:18
 */
public interface BaseService<T,PK> {

    JpaRepository<T,PK> getRepository();

    default T save(T data){
        beforeSave(data);
        return getRepository().save(data);
    }

    default void beforeSave(T data){

    }

    default void deleteById(PK id){
        getRepository().deleteById(id);
    }

    default T getById(PK id){
        return getRepository().findById(id).orElse(null);
    }

    default List<T> list(T t){
        return getRepository().findAll(Example.of(t));
    }

    default RestResp<List<T>> page(PageSort pageSort){
        return page(null,pageSort);
    }

    default RestResp<List<T>> page(T t, PageSort pageSort){
        Pageable pageable = PageRequest.of(pageSort.getPageNo(),pageSort.getPageSize());
        List<FieldSort> sort = pageSort.getSort();
        if(!sort.isEmpty()){
            Sort s = null;
            for (FieldSort fieldSort : sort) {
                Sort orders = convertSort(fieldSort);
                if(orders == null){
                    continue;
                }
                if(s == null){
                    s = orders;
                }else {
                    s = s.and(orders);
                }
            }
            if(s != null){
                pageable = PageRequest.of(pageSort.getPageNo(),pageSort.getPageSize(),s);
            }
        }
        Page<T> all;
        if(t == null){
            all = getRepository().findAll(pageable);
        }else {
            all = getRepository().findAll(Example.of(t),pageable);
        }
        return new RestResp<>(all.getContent(),all.getTotalElements());
    }

    default void updateById(PK id,T data){
        getRepository().findById(id).ifPresent(d -> getRepository().save(data));
    }

    static Sort convertSort(FieldSort fieldSort){
        if(fieldSort.getSort() == null){
            return null;
        }
        if(fieldSort.getSort().equals("asc")){
            return Sort.by(fieldSort.getField()).ascending();
        }
        return Sort.by(fieldSort.getField()).descending();
    }
}
