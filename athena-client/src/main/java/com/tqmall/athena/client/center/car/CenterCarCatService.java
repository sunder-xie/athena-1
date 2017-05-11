package com.tqmall.athena.client.center.car;

import com.tqmall.athena.domain.result.center.car.CenterCarCatDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by zxg on 16/2/3.
 * 14:56
 * 中心库车型对应的所有三级分类
 */
public interface CenterCarCatService {

    /**
     * 获得车型的商品分类列表
     * @param carId 线上车型id
     * @param vehicleCode 分类的车辆种类码，ShareConstants的常量
     * @return 列表
     */
    Result<List<CenterCarCatDTO>> getListByCarIdVehicle(Integer carId,String vehicleCode);
}
