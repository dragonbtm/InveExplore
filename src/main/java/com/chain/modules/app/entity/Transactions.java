package com.chain.modules.app.entity;

import java.math.BigDecimal;

public class Transactions {
    private BigDecimal id;

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

    public BigDecimal getId() {
        return id;
    }



    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFromaddress() {
        return fromaddress;
    }

    public void setFromaddress(String fromaddress) {
        this.fromaddress = fromaddress == null ? null : fromaddress.trim();
    }

    public String getToaddress() {
        return toaddress;
    }

    public void setToaddress(String toaddress) {
        this.toaddress = toaddress == null ? null : toaddress.trim();
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getEhash() {
        return ehash;
    }

    public void setEhash(String ehash) {
        this.ehash = ehash == null ? null : ehash.trim();
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
        this.snapshot = snapshot == null ? null : snapshot.trim();
    }

    public static final class Builder {
        private BigDecimal id;
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

        public Builder id(BigDecimal val) {
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