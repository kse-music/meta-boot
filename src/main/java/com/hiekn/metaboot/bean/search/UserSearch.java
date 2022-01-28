package com.hiekn.metaboot.bean.search;

import cn.hiboot.mcn.core.model.base.PageSort;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * UserBeanSearch
 *
 * @author DingHao
 * @since 2022/1/27 13:39
 */
@Setter
@Getter
public class UserSearch extends PageSort {
    private String name;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date starTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}
