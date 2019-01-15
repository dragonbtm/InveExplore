package com.chain.modules.app.entity;

import java.util.Date;

public class Accounts {
    private Long id;

    private Date createTime;

    private byte[] number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public byte[] getNumber() {
        return number;
    }

    public void setNumber(byte[] number) {
        this.number = number;
    }
}