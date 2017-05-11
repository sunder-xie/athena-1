package com.tqmall.athena.service.car;

import com.tqmall.athena.bean.entity.car.CarCategoryDO;
import com.tqmall.athena.bean.entity.vehicle.VehicleCategoryDO;
import com.tqmall.athena.bussiness.car.CarCategoryManager;
import com.tqmall.athena.bussiness.vehicle.VehicleManager;
import com.tqmall.athena.client.car.CarServiceExtend;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.BdUtil;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.carcategory.CarCategoryDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhangting on 16/3/24.
 */
@Slf4j
public class CarServiceExtendImpl implements CarServiceExtend {
    @Autowired
    private CarCategoryManager carCategoryManager;
    @Autowired
    private VehicleManager vehicleManager;


    @Override
    public Result<List<CarCategoryDTO>> carListByPid(Integer pid) {
        if(pid==null || pid<-1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        //"其它"的id
        if (pid == -1) {
            return handleVehicleCategory(0);
        }

        //“其它”下面的子类
        if (pid > VehicleCategoryDO.INIT_ID) {
            return handleVehicleCategory(pid);
        }

        List<CarCategoryDO> carList;
        //查询品牌级别数据
        if (pid == 0) {
            carList = carCategoryManager.getCarCategoryByPid(pid);
            List<CarCategoryDTO> categoryDTOList = BdUtil.do2bo4List(carList, CarCategoryDTO.class);
            CarCategoryDTO dto = new CarCategoryDTO();
            dto.setName("其它");
            dto.setFirstWord("其它");
            dto.setId(-1);
            dto.setPid(-1);
            dto.setLevel(1);
            dto.setCountry((byte)1);
            dto.setLogo("images/car/other_car.jpg");
            categoryDTOList.add(dto);
            return ResultUtil.successResult(categoryDTOList);
        }

        //查询车型分类
        carList = carCategoryManager.getCarCategoryByPid(pid);
        if (carList.isEmpty()) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        return ResultUtil.successResult4List(carList, CarCategoryDTO.class);
    }

    private Result<List<CarCategoryDTO>> handleVehicleCategory(Integer pid){
        Result<List<VehicleCategoryDO>> vcResult = vehicleManager.getVehicleCategoryByPid(pid);
        if(vcResult.isSuccess()){
            List<VehicleCategoryDO> vehicleCategoryDOList = vcResult.getData();
            List<CarCategoryDTO> categoryDTOList = new ArrayList<>();
            for(VehicleCategoryDO vc : vehicleCategoryDOList){
                CarCategoryDTO dto = new CarCategoryDTO();
                dto.setName(vc.getCategoryName());
                dto.setId(vc.getId());
                dto.setLevel(vc.getGrade()+1);
                dto.setPid(vc.getPid());

                categoryDTOList.add(dto);
            }
            return ResultUtil.successResult(categoryDTOList);
        }
        return ResultUtil.errorResult(vcResult);
    }
}
