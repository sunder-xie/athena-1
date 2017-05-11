package com.tqmall.athena.test.dubboTest.obd;

import com.tqmall.athena.client.obd.ObdVehicleService;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.domain.result.obd.ObdVehicleDTO;
import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.core.common.entity.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huangzhangting on 16/7/17.
 */
public class ObdVehicleTest extends BaseDubboTest {
    @Autowired
    private ObdVehicleService obdVehicleService;

    @Test
    public void test(){
        Integer vehicleId = 6022;
        Result<ObdVehicleDTO> result = obdVehicleService.getObdVehicleForCommercial(vehicleId);
        System.out.println(JsonUtil.objectToJsonStr(result));

        result = obdVehicleService.getObdVehicleById(70);
        System.out.println(JsonUtil.objectToJsonStr(result));

    }

}
