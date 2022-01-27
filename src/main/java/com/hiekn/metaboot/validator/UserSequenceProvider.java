package com.hiekn.metaboot.validator;

import com.hiekn.metaboot.bean.param.UserParam;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * describe about this class
 *
 * @author DingHao
 * @since 2021/7/27 15:10
 */
public class UserSequenceProvider implements DefaultGroupSequenceProvider<UserParam> {

    @Override
    public List<Class<?>> getValidationGroups(UserParam object) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(UserParam.class);
        if(object != null) {
            Integer type = object.getType();
            if (type != null) {
                if (1 == type) {
                    defaultGroupSequence.add(UserParam.TypeA.class);
                }else if (2 == type) {
                    defaultGroupSequence.add(UserParam.TypeB.class);
                }
            }
        }
        return defaultGroupSequence;
    }

}
