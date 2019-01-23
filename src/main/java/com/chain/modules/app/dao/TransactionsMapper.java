package com.chain.modules.app.dao;

import com.chain.modules.app.entity.Transactions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select("SELECT * FROM db_transaction_0 WHERE hash = #{hash}")
    Transactions selectByHash(String hash);
}