package com.tqmall.athena.dal.mapper.insurance;

import com.tqmall.athena.bean.entity.insurance.InsuranceCarRelDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface InsuranceCarRelDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InsuranceCarRelDO record);

    int insertSelective(InsuranceCarRelDO record);

    InsuranceCarRelDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InsuranceCarRelDO record);

    int updateByPrimaryKey(InsuranceCarRelDO record);


    List<Integer> selectCarIds(String vehicleCode);

}