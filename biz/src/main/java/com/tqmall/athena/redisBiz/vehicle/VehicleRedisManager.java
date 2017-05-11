package com.tqmall.athena.redisBiz.vehicle;

import com.tqmall.athena.bean.entity.vehicle.VehicleAttrValueDO;
import com.tqmall.athena.bean.entity.vehicle.VehicleCategoryDO;
import com.tqmall.athena.bean.entity.vehicle.VehicleDO;

import java.util.List;

/**
 * Created by huangzhangting on 16/3/23.
 */
public interface VehicleRedisManager {

    /**
     * 根据主键id查询车辆分类
     * @param id
     * @return
     */
    VehicleCategoryDO getVehicleCategoryById(Integer id);

    /**
     * 根据pid查找车辆分类
     * @param pid
     * @return
     */
    List<VehicleCategoryDO> getVehicleCategoryByPid(Integer pid);


    /**
     * 根据主键id查找车型
     * @param id
     * @return
     */
    VehicleDO getVehicleDOById(Integer id);

    /**
     * 根据pid查找，子级车型集合
     * @param pid
     * @return
     */
    List<VehicleDO> getVehicleDOByPid(Integer pid);

    /**
     * 根据车型id查找扩展属性
     * @param vehicleId
     * @return
     */
    List<VehicleAttrValueDO> getAttrValueDOByVehicleId(Integer vehicleId);

    /**
     * 根据pid查找子级车型的扩展属性
     * @param vehiclePid
     * @param vehicleIdList
     * @return
     */
    List<VehicleAttrValueDO> getAttrValueDOByVehiclePid(Integer vehiclePid, List<Integer> vehicleIdList);

    /**
     * 根据公告号，查询车型
     * @param noticeNum
     * @return
     */
    List<VehicleDO> getVehicleDOByNoticeNum(String noticeNum);
}
