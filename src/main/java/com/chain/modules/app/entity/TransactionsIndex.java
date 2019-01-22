package com.chain.modules.app.entity;

import java.util.Date;

public class TransactionsIndex {
    private Long id;

    private Integer tableindex;

    private Integer offsets;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTableindex() {
        return tableindex;
    }

    public void setTableindex(Integer tableindex) {
        this.tableindex = tableindex;
    }

    public Integer getOffsets() {
        return offsets;
    }

    public void setOffsets(Integer offsets) {
        this.offsets = offsets;
    }

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