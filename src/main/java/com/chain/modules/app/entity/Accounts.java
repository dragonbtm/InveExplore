package com.chain.modules.app.entity;

import java.util.Date;

public class Accounts {
    private Long id;

    private String number;

    private Date createTime;

    private Date updateTime;

    public Accounts() {
    }

    private Accounts(Builder builder) {
        setId(builder.id);
        setNumber(builder.number);
        setCreateTime(builder.createTime);
        setUpdateTime(builder.updateTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
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
        private String number;
        private Date createTime;
        private Date updateTime;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder number(String val) {
            number = val;
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

        public Accounts build() {
            return new Accounts(this);
        }
    }
}