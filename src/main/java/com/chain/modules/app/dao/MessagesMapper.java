package com.chain.modules.app.dao;

import com.chain.modules.app.entity.Messages;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface MessagesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Messages record);

    int insertSelective(Messages record);

    Messages selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Messages record);

    int updateByPrimaryKey(Messages record);

    @Select("SELECT * FROM db_messages msg WHERE msg.create_time = #{date,jdbcType=TIMESTAMP}")
    Messages selectByCreateTime(Date date);
}