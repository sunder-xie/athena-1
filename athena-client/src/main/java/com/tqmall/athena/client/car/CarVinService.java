package com.tqmall.athena.client.car;

import com.tqmall.athena.domain.result.carcategory.CarDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 16/6/15.
 */
public interface CarVinService {
    /**
     * 根据vin码查询车型集合
     * @param vin
     * @return
     */
    Result<List<CarDTO>> getCarListByVin(String vin);

    /**
     * 根据vin码查询车型集合,一天有次数限制，过限制，返回错误
     * @param vin vin码
     * @return
     */
    Result<List<CarDTO>> getCarListByVinLimit(String vin);
}
