package com.tqmall.athena.client.center.car;

import com.tqmall.athena.domain.result.center.car.CenterCarDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 16/2/2.
 */
public interface CenterCarService {
    Result<List<CenterCarDTO>> getCenterCarByPid(Integer pid);

    Result<List<CenterCarDTO>> getCenterCarByLevel(Integer level);

    /**
     * 根据车型id查车款集合（即：根据第三级id查询第六级数据）
     * @param modelId
     * @return
     */
    Result<List<CenterCarDTO>> getCarListByModelId(Integer modelId);

    Result<CenterCarDTO> getCenterCarById(Integer id);
}
