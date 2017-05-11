package com.tqmall.athena.dal.mapper.bank;

import com.tqmall.athena.bean.entity.bank.BankKeywordDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface BankKeywordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankKeywordDO record);

    int insertSelective(BankKeywordDO record);

    BankKeywordDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankKeywordDO record);

    int updateByPrimaryKey(BankKeywordDO record);

    List<BankKeywordDO> selectAll();
}