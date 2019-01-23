package com.chain.modules.app.entity;

import java.util.Date;

public class TransactionsIndex {
    private Long id;

    private Integer tableindex;

    private Integer offsets;

    private Date createTime;

    private Date updateTime;

    public TransactionsIndex() {
    }

    private TransactionsIndex(Builder builder) {
        setId(builder.id);
        setTableindex(builder.tableindex);
        setOffsets(builder.offsets);
        setCreateTime(builder.createTime);
        setUpdateTime(builder.updateTime);
    }

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

    public static final class Builder {
        private Long id;
        private Integer tableindex;
        private Integer offsets;
        private Date createTime;
        private Date updateTime;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder tableindex(Integer val) {
            tableindex = val;
            return this;
        }

        public Builder offsets(Integer val) {
            offsets = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public Builder updateTime(Date val) {
            updateTime = val;
            return this;
        }

        public TransactionsIndex build() {
            return new TransactionsIndex(this);
        }
    }
}