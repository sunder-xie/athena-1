package com.tqmall.athena.client.insurance;

import com.tqmall.athena.domain.result.insurance.InsuranceCompanyDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 15/11/27.
 */
public interface InsuranceService {
    /*
    *   查询全部未删除状态的保险公司信息
    * */
    Result<List<InsuranceCompanyDTO>> getInsuranceCompany();
}
