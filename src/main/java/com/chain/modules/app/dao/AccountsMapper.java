package com.chain.modules.app.dao;

import com.chain.modules.app.entity.Accounts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface AccountsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Accounts record);

    int insertSelective(Accounts record);

    Accounts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Accounts record);

    int updateByPrimaryKeyWithBLOBs(Accounts record);

    int updateByPrimaryKey(Accounts record);


    @Select("SELECT * FROM db_accounts acc WHERE acc.create_time = #{date,jdbcType=TIMESTAMP}")
    Accounts selectByDate(Date date);
}