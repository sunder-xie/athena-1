package com.tqmall.athena.bussiness.center.car;

import com.tqmall.athena.bean.entity.center.car.CenterCarCategoryDO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by zxg on 16/2/3.
 * 14:52
 */
public interface CenterCarCatManager {

    Result<List<CenterCarCategoryDO>> getListByCarIdVehicle(Integer carId, String vehicleCode);
}
