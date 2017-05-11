package com.tqmall.athena.controller;

import com.tqmall.athena.client.car.CarCategoryService;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.domain.result.carcategory.HotCarBrandDTO;
import com.tqmall.core.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zxg on 15/9/18.
 */
@RestController
@RequestMapping("/rest/test")
public class TestController {


    @Autowired
    private CarCategoryService carCategoryService;

    //测试事务管理
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result test(){

        return Result.wrapSuccessfulResult("test");
    }



    //测试事务管理
    @RequestMapping(value = "/testHotCar", method = RequestMethod.GET)
    public Result testHotCar(Integer cityId){
        Result<List<HotCarBrandDTO>> result = carCategoryService.getHotCarBrandList(cityId);

        System.out.println(JsonUtil.objectToJsonStr(result));

        return Result.wrapSuccessfulResult(result);
    }

}
