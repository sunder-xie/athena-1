package com.tqmall.athena.bussiness.insurance;

import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 16/9/19.
 */
public interface InsuranceCarRelManager {
    /**
     * 根据保险车型编码，查询淘汽车款id集合
     * @param vehicleCode 根据保险车型编码
     * @return 淘汽车款id集合
     */
    Result<List<Integer>> getCarIds(String vehicleCode);
}
