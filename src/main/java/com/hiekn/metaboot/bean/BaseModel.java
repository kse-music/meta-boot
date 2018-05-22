package com.hiekn.metaboot.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BaseModel {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8" )
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8" )
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}