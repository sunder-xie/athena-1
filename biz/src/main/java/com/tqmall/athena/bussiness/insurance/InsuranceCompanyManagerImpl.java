package com.tqmall.athena.bussiness.insurance;

import com.tqmall.athena.bean.entity.insurance.InsuranceCompanyDO;
import com.tqmall.athena.redisBiz.insurance.InsuranceRedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by huangzhangting on 15/11/27.
 */
@Service
public class InsuranceCompanyManagerImpl implements InsuranceCompanyManager{
    @Autowired
    private InsuranceRedisManager insuranceRedisManager;

    @Override
    public List<InsuranceCompanyDO> findInsuranceCompany() {
        List<InsuranceCompanyDO> list = insuranceRedisManager.getInsuranceCompany();
        if(!list.isEmpty()){
            Collections.sort(list, new Comparator<InsuranceCompanyDO>() {
                @Override
                public int compare(InsuranceCompanyDO o1, InsuranceCompanyDO o2) {
                    return o1.getSort().compareTo(o2.getSort());
                }
            });
        }
        return list;
    }
}
