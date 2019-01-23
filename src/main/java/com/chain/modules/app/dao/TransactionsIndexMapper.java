package com.chain.modules.app.dao;

import com.chain.modules.app.entity.TransactionsIndex;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TransactionsIndexMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TransactionsIndex record);

    int insertSelective(TransactionsIndex record);

    TransactionsIndex selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransactionsIndex record);

    int updateByPrimaryKey(TransactionsIndex record);

    @Select("SELECT * FROM db_transactions_index LIMIT 1;")
    TransactionsIndex selectAll();
}