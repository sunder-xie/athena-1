package com.tqmall.athena.bussiness.car;

import com.tqmall.athena.bean.bizBean.car.CarBO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 16/6/15.
 */
public interface CarVinManager {

    /**
     * 根据vin码查询车型数据
     * @param vin
     * @return
     */
    Result<List<CarBO>> getCarBOByVin(String vin);

    //限制
    Result<List<CarBO>> getCarBOByVinLimit(String vin);

}
