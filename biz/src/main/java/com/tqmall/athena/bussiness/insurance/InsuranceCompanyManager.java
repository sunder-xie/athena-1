package com.tqmall.athena.bussiness.insurance;

import com.tqmall.athena.bean.entity.insurance.InsuranceCompanyDO;

import java.util.List;

/**
 * Created by huangzhangting on 15/11/27.
 */
public interface InsuranceCompanyManager {
    List<InsuranceCompanyDO> findInsuranceCompany();
}
