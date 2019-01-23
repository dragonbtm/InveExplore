package com.chain.modules.app.dao;

import com.chain.modules.app.entity.Messages;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

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

    @Select("SELECT count(1), FROM_UNIXTIME(t.updateTime/1000,'%Y%m%d') \n" +
            "FROM db_transaction_0 t  \n" +
            "where date(FROM_UNIXTIME(t.updateTime/1000,'%Y%m%d')) >=date_sub(curdate(),interval 14 day)and DATE(FROM_UNIXTIME(t.updateTime/1000,'%Y%m%d')) <=  date_sub(CURDATE(),INTERVAL 1 DAY)\n" +
            "GROUP BY FROM_UNIXTIME(t.updateTime/1000,'%Y%m%d') \n" +
            "ORDER BY date(FROM_UNIXTIME(t.updateTime/1000,'%Y%m%d')) ASC")
    List<Integer> selectTrans();
}