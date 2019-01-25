package com.chain.modules.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chain.modules.app.entity.Transactions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface TransactionsMapper extends BaseMapper<Transactions> {
    int deleteByPrimaryKey(BigDecimal id);

    Integer insert(Transactions record);

    int insertSelective(Transactions record);

    Transactions selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(Transactions record);

    int updateByPrimaryKey(Transactions record);

    int selectMessageTotal();

    @Select("SELECT * FROM db_transaction_0 WHERE hash = #{hash}")
    Transactions selectByHash(String hash);

    List<Object> selectByAddress(Map<String,Object> params);

    @Select("SELECT count(1) FROM db_transaction_0 WHERE fromAddress = #{address} or toAddress = #{address}")
    Integer selectMessageTotalByAddress(String address);
}