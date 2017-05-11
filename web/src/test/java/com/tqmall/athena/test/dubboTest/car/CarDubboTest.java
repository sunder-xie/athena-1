package com.tqmall.athena.test.dubboTest.car;

import com.tqmall.athena.client.car.CarCategoryService;
import com.tqmall.athena.client.car.CarVinService;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.domain.result.carcategory.*;
import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.core.common.entity.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by zxg on 15/9/22.
 */
public class CarDubboTest extends BaseDubboTest {

    @Autowired
    private CarCategoryService carCategoryService;

    @Autowired
    private CarVinService carVinService;


    @Test
    public void testGetPrimaryCarCategory(){
        Integer id = 48786;
        Result<CarCategoryDTO> result = carCategoryService.getCarCategoryByPrimaryId(id);

        System.out.println(JsonUtil.objectToJsonStr(result));
    }

    @Test
    public void testGetAllCarCategory(){
        Result<List<CarCategoryDTO>> result = carCategoryService.getAllCarCategory();
        List<CarCategoryDTO> list = result.getData();
        System.out.println("=====================testGetAllCarCategory:"+list.size());
        for(CarCategoryDTO carCategoryDTO:list){
            System.out.println(carCategoryDTO.getId()+" "+carCategoryDTO.getName()+" pid:"+carCategoryDTO.getPid());
        }
        System.out.println("=====================end:");
        System.out.println(JsonUtil.objectToJsonStr(result));
        assertTrue(list.size()>0);
    }

    @Test
    public void testGetCarCategoryByPid(){
        Integer pid = 70158;
        Result<List<CarCategoryDTO>> result = carCategoryService.getCarCategoryByPid(pid);
        List<CarCategoryDTO> list = result.getData();
        System.out.println("=====================testGetCarCategoryByPid:"+list.size());
        for(CarCategoryDTO carCategoryDTO:list){
            System.out.println(carCategoryDTO.getId()+" "+carCategoryDTO.getName()+" pid:"+carCategoryDTO.getPid()+" level:");
        }
        System.out.println(JsonUtil.objectToJsonStr(list));

        System.out.println("=====================end:");

        assertTrue(list.size()>0);
    }

    @Test
    public void testGetCarParentsByCarId(){
        Integer carId = 48761;
        Result<List<CarCategoryDTO>> result = carCategoryService.getCarParentsByCarId(carId);
        List<CarCategoryDTO> list = result.getData();
        System.out.println("=====================testGetCarParentsByCarId:");
        for(CarCategoryDTO carCategoryDTO:list){
            System.out.println(carCategoryDTO.getId()+" "+carCategoryDTO.getName()+" pid:"+carCategoryDTO.getPid());
        }
        System.out.println("=====================end:");

        assertTrue(list.size()>0);
    }

    @Test
    public void testQueryCarCatInfoByLId(){
        String liyangId = "ACC0133A0001";
        Result<CarCategoryDTO> result = carCategoryService.queryCarCatInfoByLId(liyangId);
        CarCategoryDTO carCategoryDTO = result.getData();
        System.out.println("=====================testQueryCarCatInfoByLId:");
        System.out.println(carCategoryDTO.getId()+" "+carCategoryDTO.getName()+" :level"+ carCategoryDTO.getLevel()+" pid:"+carCategoryDTO.getPid());

        assertTrue(carCategoryDTO != null);
    }


    @Test
    public void testCategoryCarInfo(){
//        Integer pid = 87;//马自达
//        Integer pid = 9;//奥迪 level 1
//        Integer pid = 188;//奥迪 进口80 level 2
        Integer pid = 76237;//奥迪 进口 A8L level 3

        Result<List<CarListDTO>> result = carCategoryService.categoryCarInfo(pid);
        List<CarListDTO> list = result.getData();
        System.out.println("=====================testCategoryCarInfo:" + list.size());
        for(CarListDTO carCategoryDTO:list){
            System.out.println(carCategoryDTO.getId()+" "+carCategoryDTO.getCarName()+" pid:"+carCategoryDTO.getPid());
        }
        System.out.println("=====================end:");
        assertTrue(list.size()>0);
    }

    @Test
    public void testGetCartListByGoodsId(){
        Integer goodsId = 19957;
        Result<List<CarListSuit4GoodsDTO>> result = carCategoryService.getCartListByGoodsId(goodsId);
        List<CarListSuit4GoodsDTO> list = result.getData();
        System.out.println("=====================testGetCarParentsByCarId:"+list.size());

        System.out.println(JsonUtil.objectToJsonStr(list));
        assertTrue(list.size() > 0);
    }


    @Test
    public void testHotCar(){
        Integer cityId = 52;
        Result<List<HotCarBrandDTO>> result = carCategoryService.getHotCarBrandList(cityId);

        System.out.println(JsonUtil.objectToJsonStr(result));
    }

    @Test
    public void testCar(){
        Integer level = 2;
        Result<List<CarCategoryDTO>> result = carCategoryService.getByLevel(level);

        System.out.println(JsonUtil.objectToJsonStr(result));


        List<Integer> pid = new ArrayList<>();
        pid.add(149);
        result = carCategoryService.getByPidList(pid);

        System.out.println(JsonUtil.objectToJsonStr(result));
    }


    @Test
    public void testVin(){
        String vin = "LFV2B21KXC3311106";
        Result<List<CarDTO>> result = carVinService.getCarListByVin(vin);
        System.out.println(JsonUtil.objectToJsonStr(result));
    }

}
