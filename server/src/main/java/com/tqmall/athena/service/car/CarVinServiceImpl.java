package com.tqmall.athena.service.car;

import com.tqmall.athena.bean.bizBean.car.CarBO;
import com.tqmall.athena.bussiness.car.CarVinManager;
import com.tqmall.athena.client.car.CarVinService;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.carcategory.CarDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangzhangting on 16/6/15.
 */
@Slf4j
public class CarVinServiceImpl implements CarVinService {
    @Autowired
    private CarVinManager carVinManager;

    @Override
    public Result<List<CarDTO>> getCarListByVin(String vin) {
        Result<List<CarBO>> result = carVinManager.getCarBOByVin(vin);
        return ResultUtil.handleResult4List(result, CarDTO.class);
    }

    @Override
    public Result<List<CarDTO>> getCarListByVinLimit(String vin) {
        Result<List<CarBO>> result = carVinManager.getCarBOByVinLimit(vin);
        return ResultUtil.handleResult4List(result, CarDTO.class);
    }
}
