package com.tqmall.athena.bussiness.vehicle;

import com.tqmall.athena.bean.bizBean.vehicle.VehicleAttrValueBO;
import com.tqmall.athena.bean.bizBean.vehicle.VehicleBO;
import com.tqmall.athena.bean.entity.vehicle.VehicleCategoryDO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * 商用车biz
 * Created by huangzhangting on 16/3/23.
 */
public interface VehicleManager {
    /**
     * 根据主键id查询车辆分类
     * @param id
     * @return
     */
    Result<VehicleCategoryDO> getVehicleCategoryById(Integer id);

    /**
     * 根据pid查询，车辆分类
     * @param pid
     * @return
     */
    Result<List<VehicleCategoryDO>> getVehicleCategoryByPid(Integer pid);


    /**
     * 根据id查询车型
     * @param id
     * @return
     */
    Result<VehicleBO> getVehicleBOById(Integer id);

    /**
     * 根据父级id查询子集车型
     * @param pid
     * @return
     */
    Result<List<VehicleBO>> getVehicleBOByPid(Integer pid);

    /**
     * 根据车型id，查询扩展属性
     * @param vehicleId
     * @return
     */
    Result<List<VehicleAttrValueBO>> getExtAttrByVehicleId(Integer vehicleId);

    /**
     * 根据公告号，查询车型数据
     * @param noticeNum
     * @return
     */
    Result<List<VehicleBO>> getVehicleBOByNoticeNum(String noticeNum);
}
