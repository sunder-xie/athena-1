package com.tqmall.athena.redisBiz.center.car;

import com.tqmall.athena.bean.entity.center.car.CenterCarDO;

import java.util.List;

/**
 * Created by huangzhangting on 16/2/2.
 */
public interface CenterCarRedisManager {
    List<CenterCarDO> getCenterCarByPid(Integer pid);

    List<CenterCarDO> getCenterCarByLevel(Integer level);

    /**
     * 根据车型id查车款集合（即：根据第三级id查询第六级数据）
     * @param modelId
     * @return
     */
    List<CenterCarDO> getCarListByModelId(Integer modelId);

    CenterCarDO getCenterCarById(Integer id);

}
