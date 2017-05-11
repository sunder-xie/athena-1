package com.tqmall.athena.service.center.car;

import com.tqmall.athena.bean.entity.center.car.CenterCarCategoryDO;
import com.tqmall.athena.bussiness.center.car.CenterCarCatManager;
import com.tqmall.athena.client.center.car.CenterCarCatService;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.center.car.CenterCarCatDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zxg on 16/2/3.
 * 14:57
 */
@Slf4j
public class CenterCarCatServiceImpl implements CenterCarCatService {

    @Autowired
    private CenterCarCatManager centerCarCatManager;

    @Override
    public Result<List<CenterCarCatDTO>> getListByCarIdVehicle(Integer carId, String vehicleCode){
        Result<List<CenterCarCategoryDO>> result = centerCarCatManager.getListByCarIdVehicle(carId, vehicleCode);
        return ResultUtil.handleResult4List(result, CenterCarCatDTO.class);
    }
}
