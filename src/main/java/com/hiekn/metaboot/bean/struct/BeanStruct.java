package com.hiekn.metaboot.bean.struct;


import com.hiekn.metaboot.bean.param.UserParam;
import com.hiekn.metaboot.bean.po.User;
import org.mapstruct.Mapper;

/**
 * bean转换
 *
 * @author DingHao
 * @since 2021/2/23 22:16
 */
@Mapper(componentModel = "spring")
public interface BeanStruct {

    User toUser(UserParam userParam);

}
