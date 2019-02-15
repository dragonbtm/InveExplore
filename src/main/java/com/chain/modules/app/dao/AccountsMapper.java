package com.chain.modules.app.dao;

import com.chain.modules.app.entity.Accounts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

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


    @Select("SELECT number, a.create_time \n" +
        "FROM db_accounts a  \n" +
        "WHERE DATE(a.create_time) >=DATE_SUB(curdate(),interval 14 day) and DATE(a.create_time) <=  DATE_SUB(CURDATE(),INTERVAL 1 DAY)\n" +
        "ORDER BY date(a.create_time) ASC")
    List<Integer> selectAccs();

    @Select("SELECT number, a.create_time \n" +
            "FROM db_accounts a   \n" +
            "ORDER BY date(a.create_time) DESC")
    List<Accounts> selectList();
}