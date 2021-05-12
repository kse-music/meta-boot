package com.hiekn.metaboot.base;

import lombok.Getter;
import lombok.Setter;

/**
 * describe about this class
 *
 * @author DingHao
 * @since 2021/2/8 17:53
 */
@Setter
@Getter
public class FieldSort {
    private String field;
    /**
     * asc:升序
     * desc:降序
     */
    private String sort;
}
