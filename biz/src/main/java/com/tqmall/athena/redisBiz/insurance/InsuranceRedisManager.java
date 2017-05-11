package com.tqmall.athena.redisBiz.insurance;

import com.tqmall.athena.bean.entity.insurance.InsuranceCompanyDO;

import java.util.List;

/**
 * Created by huangzhangting on 15/11/27.
 */
public interface InsuranceRedisManager {
    List<InsuranceCompanyDO> getInsuranceCompany();

    /**
     * 根据保险车型编码，查询淘汽车款id集合
     * @param vehicleCode 根据保险车型编码
     * @return 淘汽车款id集合
     */
    List<Integer> getCarIds(String vehicleCode);
}
