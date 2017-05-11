package com.tqmall.athena.bussiness.center.car;

import com.tqmall.athena.bean.entity.center.car.CenterCarDO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 16/2/2.
 */
public interface CenterCarManager {
    Result<List<CenterCarDO>> getCenterCarByPid(Integer pid);

    Result<List<CenterCarDO>> getCenterCarByLevel(Integer level);

    /**
     * 根据车型id查车款集合（即：根据第三级id查询第六级数据）
     * @param modelId
     * @return
     */
    Result<List<CenterCarDO>> getCarListByModelId(Integer modelId);

    Result<CenterCarDO> getCenterCarById(Integer id);
}
