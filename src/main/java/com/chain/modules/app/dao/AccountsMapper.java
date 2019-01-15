package com.chain.modules.app.dao;

import com.chain.modules.app.entity.Accounts;

public interface AccountsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Accounts record);

    int insertSelective(Accounts record);

    Accounts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Accounts record);

    int updateByPrimaryKeyWithBLOBs(Accounts record);

    int updateByPrimaryKey(Accounts record);
}