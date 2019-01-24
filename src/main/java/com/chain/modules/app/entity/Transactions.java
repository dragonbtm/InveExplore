package com.chain.modules.app.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.math.BigInteger;

@TableName("db_transaction_0")
public class Transactions {
    private BigInteger id;

    private String fromaddress;

    private String toaddress;

    private String hash;

    private String type;

    private String ehash;

    private Long isvalid;

    private Long updatetime;

    private String snapshot;


    public Transactions() {
    }

    private Transactions(Builder builder) {
        setId(builder.id);
        setFromaddress(builder.fromaddress);
        setToaddress(builder.toaddress);
        setHash(builder.hash);
        setType(builder.type);
        setEhash(builder.ehash);
        setIsvalid(builder.isvalid);
        setUpdatetime(builder.updatetime);
        setSnapshot(builder.snapshot);
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFromaddress() {
        return fromaddress;
    }

    public void setFromaddress(String fromaddress) {
        this.fromaddress = fromaddress;
    }

    public String getToaddress() {
        return toaddress;
    }

    public void setToaddress(String toaddress) {
        this.toaddress = toaddress;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEhash() {
        return ehash;
    }

    public void setEhash(String ehash) {
        this.ehash = ehash;
    }

    public Long getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Long isvalid) {
        this.isvalid = isvalid;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public static final class Builder {
        private BigInteger id;
        private String fromaddress;
        private String toaddress;
        private String hash;
        private String type;
        private String ehash;
        private Long isvalid;
        private Long updatetime;
        private String snapshot;

        public Builder() {
        }

        public Builder id(BigInteger val) {
            id = val;
            return this;
        }

        public Builder fromaddress(String val) {
            fromaddress = val;
            return this;
        }

        public Builder toaddress(String val) {
            toaddress = val;
            return this;
        }

        public Builder hash(String val) {
            hash = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder ehash(String val) {
            ehash = val;
            return this;
        }

        public Builder isvalid(Long val) {
            isvalid = val;
            return this;
        }

        public Builder updatetime(Long val) {
            updatetime = val;
            return this;
        }

        public Builder snapshot(String val) {
            snapshot = val;
            return this;
        }

        public Transactions build() {
            return new Transactions(this);
        }
    }
}