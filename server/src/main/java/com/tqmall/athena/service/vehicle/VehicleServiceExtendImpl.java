package com.tqmall.athena.service.vehicle;

import com.tqmall.athena.bean.bizBean.vehicle.VehicleAttrValueBO;
import com.tqmall.athena.bean.bizBean.vehicle.VehicleBO;
import com.tqmall.athena.bean.common.ShareConstants;
import com.tqmall.athena.bean.entity.vehicle.VehicleCategoryDO;
import com.tqmall.athena.bussiness.vehicle.VehicleManager;
import com.tqmall.athena.client.vehicle.VehicleServiceExtend;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.vehicle.VehicleAttrValueDTO;
import com.tqmall.athena.domain.result.vehicle.VehicleDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhangting on 16/6/17.
 */
@Slf4j
public class VehicleServiceExtendImpl implements VehicleServiceExtend {
    @Autowired
    private VehicleManager vehicleManager;

    @Override
    public Result<VehicleDTO> getVehicleById(Integer id) {
        if(id==null || id<1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        //“其他”下面车辆类型的id
        if(id > VehicleCategoryDO.INIT_ID){
            return handleVehicleCategoryById(id);
        }

        Result<VehicleBO> result = vehicleManager.getVehicleBOById(id);
        return ResultUtil.handleResult(result, VehicleDTO.class);
    }

    @Override
    public Result<List<VehicleDTO>> getVehicleByPid(Integer pid) {
        if(pid==null || pid<0){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        //"其它"的id
        if(pid == VehicleCategoryDO.INIT_ID){
            return handleVehicleCategoryByPid(0);
        }
        //“其它”下面的子类
        if (pid > VehicleCategoryDO.INIT_ID) {
            return handleVehicleCategoryByPid(pid);
        }

        Result<List<VehicleBO>> result = vehicleManager.getVehicleBOByPid(pid);
        if(!result.isSuccess()){
            return ResultUtil.errorResult(result);
        }
        List<VehicleBO> list = result.getData();
        //查询品牌级别数据
        if(pid==0){
            VehicleBO vehicleBO = new VehicleBO();
            vehicleBO.setId(VehicleCategoryDO.INIT_ID);
            vehicleBO.setPid(0);
            vehicleBO.setGrade((short)1);
            vehicleBO.setVehicleName("其它");
            vehicleBO.setFirstWord("其它");
            vehicleBO.setCategoryName("其它");
            vehicleBO.setLogo(ShareConstants.HTTP_IMG_URL + "images/car/other_car.jpg");
            vehicleBO.setHasExtendAttr(false);

            list.add(vehicleBO);
        }
        return ResultUtil.successResult4List(list, VehicleDTO.class);
    }

    @Override
    public Result<List<VehicleAttrValueDTO>> getExtAttrByVehicleId(Integer vehicleId) {
        Result<List<VehicleAttrValueBO>> result = vehicleManager.getExtAttrByVehicleId(vehicleId);
        return ResultUtil.handleResult4List(result, VehicleAttrValueDTO.class);
    }


    // 将“其它”下面的车辆分类数据，转换成商用车车型数据
    // ================================ start =================================
    private VehicleDTO handleVehicleCategory(VehicleCategoryDO categoryDO){
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(categoryDO.getId());
        vehicleDTO.setVehicleName(categoryDO.getCategoryName());
        vehicleDTO.setPid(categoryDO.getPid());
        vehicleDTO.setGrade((short)(categoryDO.getGrade()+1));
        vehicleDTO.setCategoryName("其它");
        vehicleDTO.setHasExtendAttr(false);
        return vehicleDTO;
    }

    private Result<VehicleDTO> handleVehicleCategoryById(Integer id){
        Result<VehicleCategoryDO> result = vehicleManager.getVehicleCategoryById(id);
        if(result.isSuccess()){
            return ResultUtil.successResult(handleVehicleCategory(result.getData()));
        }
        return ResultUtil.errorResult(result);
    }

    private Result<List<VehicleDTO>> handleVehicleCategoryByPid(Integer pid){
        Result<List<VehicleCategoryDO>> result = vehicleManager.getVehicleCategoryByPid(pid);
        if(result.isSuccess()){
            List<VehicleCategoryDO> vehicleCategoryDOList = result.getData();
            List<VehicleDTO> list = new ArrayList<>();
            for(VehicleCategoryDO vc : vehicleCategoryDOList){
                list.add(handleVehicleCategory(vc));
            }
            return ResultUtil.successResult(list);
        }
        return ResultUtil.errorResult(result);
    }

    // ================================ end =================================


    @Override
    public Result<List<VehicleDTO>> getVehicleByNoticeNum(String noticeNum) {
        Result<List<VehicleBO>> result = vehicleManager.getVehicleBOByNoticeNum(noticeNum);
        return ResultUtil.handleResult4List(result, VehicleDTO.class);
    }

}
