package com.chain.modules.app.entity;

import java.util.Date;

public class Transactions {
    private String id;

    private Date creationDate;

    private Long amount;

    private Long fee;

    private Long amountPoint;

    private Long feePoint;

    private String addressfrom;

    private String addressto;

    private Integer type;

    private Integer stype;

    private Integer etype;

    private Double percent;

    private Short sconfirm;

    private Short econfirm;

    private Short sstatu;

    private Short estatu;

    private String result;

    private String remark;

    private String shash;

    private String ehash;

    private String sinfo;

    private String einfo;

    private String multihash;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getAmountPoint() {
        return amountPoint;
    }

    public void setAmountPoint(Long amountPoint) {
        this.amountPoint = amountPoint;
    }

    public Long getFeePoint() {
        return feePoint;
    }

    public void setFeePoint(Long feePoint) {
        this.feePoint = feePoint;
    }

    public String getAddressfrom() {
        return addressfrom;
    }

    public void setAddressfrom(String addressfrom) {
        this.addressfrom = addressfrom == null ? null : addressfrom.trim();
    }

    public String getAddressto() {
        return addressto;
    }

    public void setAddressto(String addressto) {
        this.addressto = addressto == null ? null : addressto.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStype() {
        return stype;
    }

    public void setStype(Integer stype) {
        this.stype = stype;
    }

    public Integer getEtype() {
        return etype;
    }

    public void setEtype(Integer etype) {
        this.etype = etype;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Short getSconfirm() {
        return sconfirm;
    }

    public void setSconfirm(Short sconfirm) {
        this.sconfirm = sconfirm;
    }

    public Short getEconfirm() {
        return econfirm;
    }

    public void setEconfirm(Short econfirm) {
        this.econfirm = econfirm;
    }

    public Short getSstatu() {
        return sstatu;
    }

    public void setSstatu(Short sstatu) {
        this.sstatu = sstatu;
    }

    public Short getEstatu() {
        return estatu;
    }

    public void setEstatu(Short estatu) {
        this.estatu = estatu;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getShash() {
        return shash;
    }

    public void setShash(String shash) {
        this.shash = shash == null ? null : shash.trim();
    }

    public String getEhash() {
        return ehash;
    }

    public void setEhash(String ehash) {
        this.ehash = ehash == null ? null : ehash.trim();
    }

    public String getSinfo() {
        return sinfo;
    }

    public void setSinfo(String sinfo) {
        this.sinfo = sinfo == null ? null : sinfo.trim();
    }

    public String getEinfo() {
        return einfo;
    }

    public void setEinfo(String einfo) {
        this.einfo = einfo == null ? null : einfo.trim();
    }

    public String getMultihash() {
        return multihash;
    }

    public void setMultihash(String multihash) {
        this.multihash = multihash == null ? null : multihash.trim();
    }
}