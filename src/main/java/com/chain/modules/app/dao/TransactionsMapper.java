package com.chain.modules.app.dao;

import com.chain.modules.app.entity.Transactions;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Transactions record);

    int insertSelective(Transactions record);

    Transactions selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Transactions record);

    int updateByPrimaryKeyWithBLOBs(Transactions record);

    int updateByPrimaryKey(Transactions record);
}