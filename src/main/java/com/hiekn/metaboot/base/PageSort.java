package com.hiekn.metaboot.base;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * describe about this class
 *
 * @author DingHao
 * @since 2021/2/8 17:45
 */
@Setter
@Getter
public class PageSort {

    private int pageNo = 1;
    private int pageSize = 10;

    private List<FieldSort> sort = new ArrayList<>(1);

    public int getPageNo() {
        return pageNo - 1;
    }
}
