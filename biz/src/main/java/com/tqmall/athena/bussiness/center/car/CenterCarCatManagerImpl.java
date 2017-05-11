package com.tqmall.athena.bussiness.center.car;

import com.tqmall.athena.bean.entity.center.car.CenterCarCategoryDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.redisBiz.center.car.CenterCarCatRedisManager;
import com.tqmall.core.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zxg on 16/2/3.
 * 14:53
 */
@Service
public class CenterCarCatManagerImpl implements CenterCarCatManager {

    @Autowired
    private CenterCarCatRedisManager centerCarCatRedisManager;

    @Override
    public Result<List<CenterCarCategoryDO>> getListByCarIdVehicle(Integer carId, String vehicleCode) {
        if (carId == null || carId < 1 || vehicleCode == null) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<CenterCarCategoryDO> list = centerCarCatRedisManager.getListByCarIdVehicle(carId, vehicleCode);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return ResultUtil.successResult(list);
    }
}
