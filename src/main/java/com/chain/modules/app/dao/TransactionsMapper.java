package com.chain.modules.app.dao;

import com.chain.modules.app.entity.Transactions;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface TransactionsMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(Transactions record);

    int insertSelective(Transactions record);

    Transactions selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(Transactions record);

    int updateByPrimaryKey(Transactions record);

    int selectMessageTotal();
}