package com.tqmall.athena.service.insurance;

import com.tqmall.athena.bean.entity.insurance.InsuranceCompanyDO;
import com.tqmall.athena.bussiness.insurance.InsuranceCompanyManager;
import com.tqmall.athena.client.insurance.InsuranceService;
import com.tqmall.athena.common.utils.BdUtil;
import com.tqmall.athena.domain.result.insurance.InsuranceCompanyDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangzhangting on 15/11/27.
 */
@Slf4j
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    private InsuranceCompanyManager companyManager;

    @Override
    public Result<List<InsuranceCompanyDTO>> getInsuranceCompany() {
        List<InsuranceCompanyDO> companyDOList = companyManager.findInsuranceCompany();
        List<InsuranceCompanyDTO> resultList = BdUtil.do2bo4List(companyDOList, InsuranceCompanyDTO.class);
        return Result.wrapSuccessfulResult(resultList);
    }
}
