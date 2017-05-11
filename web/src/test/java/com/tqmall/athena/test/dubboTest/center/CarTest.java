package com.tqmall.athena.test.dubboTest.center;

import com.tqmall.athena.bean.common.ShareConstants;
import com.tqmall.athena.client.center.car.CenterCarCatService;
import com.tqmall.athena.client.center.car.CenterCarService;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.domain.result.center.car.CenterCarCatDTO;
import com.tqmall.athena.domain.result.center.car.CenterCarDTO;
import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.core.common.entity.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangzhangting on 16/2/2.
 */
public class CarTest extends BaseDubboTest {
    @Autowired
    private CenterCarService centerCarService;
    @Autowired
    private CenterCarCatService centerCarCatService;

    @Test
    public void test(){
        System.out.println(" \n=======================================\n ");

//        Result<List<CenterCarDTO>> result = centerCarService.getCenterCarByPid(0);


        Result<CenterCarDTO> result = centerCarService.getCenterCarById(54872);


        System.out.println(JsonUtil.objectToJsonStr(result));

        System.out.println(" \n=======================================\n ");
    }


    @Test
    public void testCat(){
        System.out.println(" \n=======================================\n ");

        Result<List<CenterCarCatDTO>> result = centerCarCatService.getListByCarIdVehicle(null, ShareConstants.PASSENGER_CAR);
        System.out.println(JsonUtil.objectToJsonStr(result));




        System.out.println(" \n=======================================\n ");
    }
}
