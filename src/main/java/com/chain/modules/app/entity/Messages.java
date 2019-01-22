package com.chain.modules.app.entity;

import java.util.Date;

public class Messages {
    private Long id;

    private String runtime;

    private String shardnumber;

    private String tps;

    private Long usercount;

    private Date createTime;

    private Date updateTime;

    public Messages() {
    }

    private Messages(Builder builder) {
        setId(builder.id);
        setRuntime(builder.runtime);
        setShardnumber(builder.shardnumber);
        setTps(builder.tps);
        setUsercount(builder.usercount);
        setCreateTime(builder.createTime);
        setUpdateTime(builder.updateTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime == null ? null : runtime.trim();
    }

    public String getShardnumber() {
        return shardnumber;
    }

    public void setShardnumber(String shardnumber) {
        this.shardnumber = shardnumber == null ? null : shardnumber.trim();
    }

    public String getTps() {
        return tps;
    }

    public void setTps(String tps) {
        this.tps = tps == null ? null : tps.trim();
    }

    public Long getUsercount() {
        return usercount;
    }

    public void setUsercount(Long usercount) {
        this.usercount = usercount;
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
        private String runtime;
        private String shardnumber;
        private String tps;
        private Long usercount;
        private Date createTime;
        private Date updateTime;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder runtime(String val) {
            runtime = val;
            return this;
        }

        public Builder shardnumber(String val) {
            shardnumber = val;
            return this;
        }

        public Builder tps(String val) {
            tps = val;
            return this;
        }

        public Builder usercount(Long val) {
            usercount = val;
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

        public Messages build() {
            return new Messages(this);
        }
    }
}