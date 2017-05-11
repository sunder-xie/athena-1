package com.tqmall.athena.bussiness.vehicle;

import com.tqmall.athena.bean.bizBean.vehicle.VehicleAttrValueBO;
import com.tqmall.athena.bean.bizBean.vehicle.VehicleBO;
import com.tqmall.athena.bean.entity.vehicle.VehicleAttrValueDO;
import com.tqmall.athena.bean.entity.vehicle.VehicleCategoryDO;
import com.tqmall.athena.bean.entity.vehicle.VehicleDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.BdUtil;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.redisBiz.vehicle.VehicleRedisManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by huangzhangting on 16/3/23.
 */
@Service
@Slf4j
public class VehicleManagerImpl implements VehicleManager {
    @Autowired
    private VehicleRedisManager vehicleRedisManager;


    @Override
    public Result<VehicleCategoryDO> getVehicleCategoryById(Integer id) {
        if(id==null || id<=VehicleCategoryDO.INIT_ID){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        VehicleCategoryDO categoryDO = vehicleRedisManager.getVehicleCategoryById(id);
        if(categoryDO==null){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        return ResultUtil.successResult(categoryDO);
    }

    @Override
    public Result<List<VehicleCategoryDO>> getVehicleCategoryByPid(Integer pid) {
        if(pid==null || pid<0){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        List<VehicleCategoryDO> list = vehicleRedisManager.getVehicleCategoryByPid(pid);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        Collections.sort(list, new Comparator<VehicleCategoryDO>() {
            @Override
            public int compare(VehicleCategoryDO o1, VehicleCategoryDO o2) {
                return o1.getSort().compareTo(o2.getSort());
            }
        });
        return ResultUtil.successResult(list);
    }

    @Override
    public Result<VehicleBO> getVehicleBOById(Integer id) {
        if(id==null || id<=0){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        VehicleDO vehicleDO = vehicleRedisManager.getVehicleDOById(id);
        if(vehicleDO==null){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        VehicleBO vehicleBO = BdUtil.do2bo(vehicleDO, VehicleBO.class);

        return Result.wrapSuccessfulResult(vehicleBO);
    }

    @Override
    public Result<List<VehicleBO>> getVehicleBOByPid(Integer pid) {
        if(pid==null || pid<0){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<VehicleDO> list = vehicleRedisManager.getVehicleDOByPid(pid);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return ResultUtil.successResult4List(list, VehicleBO.class);
    }

    @Override
    public Result<List<VehicleAttrValueBO>> getExtAttrByVehicleId(Integer vehicleId) {
        if(vehicleId==null || vehicleId<=0){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        List<VehicleAttrValueDO> list = vehicleRedisManager.getAttrValueDOByVehicleId(vehicleId);
        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return ResultUtil.successResult4List(list, VehicleAttrValueBO.class);
    }

    @Override
    public Result<List<VehicleBO>> getVehicleBOByNoticeNum(String noticeNum) {
        if(StringUtils.isEmpty(noticeNum)){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        List<VehicleDO> doList = vehicleRedisManager.getVehicleDOByNoticeNum(noticeNum);
        if(doList.isEmpty()){
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }

        return ResultUtil.successResult4List(doList, VehicleBO.class);
    }
}
