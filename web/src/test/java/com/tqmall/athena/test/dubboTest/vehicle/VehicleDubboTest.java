package com.tqmall.athena.test.dubboTest.vehicle;

import com.tqmall.athena.client.car.CarServiceExtend;
import com.tqmall.athena.client.vehicle.VehicleService;
import com.tqmall.athena.client.vehicle.VehicleServiceExtend;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.domain.result.carcategory.CarCategoryDTO;
import com.tqmall.athena.domain.result.vehicle.VehicleAttrValueDTO;
import com.tqmall.athena.domain.result.vehicle.VehicleDTO;
import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.core.common.entity.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by huangzhangting on 15/9/21.
 */
public class VehicleDubboTest extends BaseDubboTest {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private CarServiceExtend carServiceExtend;

    @Autowired
    private VehicleServiceExtend vehicleServiceExtend;


    @Test
    public void test() throws Exception{
//        Result<List<VehicleCategoryDTO>> result = vehicleService.getVehicleCategoryByPid(500024);
//        System.out.println(JsonUtil.objectToJsonStr(result));

        Result<List<CarCategoryDTO>> result1 = carServiceExtend.carListByPid(0);
        System.out.println(JsonUtil.objectToJsonStr(result1));

    }

    @Test
    public void test2() throws Exception {
//        Result<VehicleDTO> result = vehicleService.getVehicleById(1335);
//        System.out.println(JsonUtil.objectToJsonStr(result));
//
//        result = vehicleService.getVehicleById(77);
//        System.out.println(JsonUtil.objectToJsonStr(result));
//
//        result = vehicleService.getVehicleById(133);
//        System.out.println(JsonUtil.objectToJsonStr(result));
//
        Integer id = 1;
        Result<VehicleDTO> result1 = vehicleService.getVehicleById(id);
        System.out.println(JsonUtil.objectToJsonStr(result1));

        Result<List<VehicleDTO>> listResult = vehicleService.getVehicleByPid(1950);
        System.out.println(JsonUtil.objectToJsonStr(listResult));

        listResult = vehicleService.getVehicleByPid(6928);
        System.out.println(JsonUtil.objectToJsonStr(listResult));

        Result<List<VehicleAttrValueDTO>> result = vehicleService.getExtAttrByVehicleId(7479);
        System.out.println(JsonUtil.objectToJsonStr(result));
    }

    @Test
    public void test3() throws Exception{
        Result<List<VehicleDTO>> result;

//        result = vehicleServiceExtend.getVehicleByPid(0);
//        System.out.println(JsonUtil.objectToJsonStr(result));


//        result = vehicleServiceExtend.getVehicleByPid(-1);
//        System.out.println(JsonUtil.objectToJsonStr(result));
//
//        Result<VehicleDTO> result1 = vehicleServiceExtend.getVehicleById(123);
//        System.out.println(JsonUtil.objectToJsonStr(result1));

        String num = "BJ1043V8JD5-S1";
        result = vehicleServiceExtend.getVehicleByNoticeNum(num);
        System.out.println(JsonUtil.objectToJsonStr(result));

    }

}
