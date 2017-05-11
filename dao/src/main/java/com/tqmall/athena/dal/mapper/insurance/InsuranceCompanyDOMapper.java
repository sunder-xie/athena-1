package com.tqmall.athena.dal.mapper.insurance;

import com.tqmall.athena.bean.entity.insurance.InsuranceCompanyDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface InsuranceCompanyDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InsuranceCompanyDO record);

    int insertSelective(InsuranceCompanyDO record);

    InsuranceCompanyDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InsuranceCompanyDO record);

    int updateByPrimaryKey(InsuranceCompanyDO record);

    List<InsuranceCompanyDO> selectInsuranceCompany();
}