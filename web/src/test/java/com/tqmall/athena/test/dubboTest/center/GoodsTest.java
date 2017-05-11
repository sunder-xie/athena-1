package com.tqmall.athena.test.dubboTest.center;

import com.tqmall.athena.client.center.goods.CenterGoodsService;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.domain.result.center.goods.CenterGoodsCarDTO;
import com.tqmall.athena.domain.result.center.goods.CenterGoodsCarDetailDTO;
import com.tqmall.athena.domain.result.center.goods.CenterGoodsCarSubjoinDTO;
import com.tqmall.athena.domain.result.center.goods.CenterGoodsDTO;
import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.core.common.entity.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by huangzhangting on 16/2/2.
 */
public class GoodsTest extends BaseDubboTest {
    @Autowired
    private CenterGoodsService centerGoodsService;

    @Test
    public void test(){
        System.out.println(" \n=======================================\n ");

//        Result<CenterGoodsDTO> result = centerGoodsService.getGoodsByOeNumber("928001X0004W");
//
//        Result<List<CenterGoodsCarDetailDTO>> result = centerGoodsService.getGoodsCarByGoodsId(10);
//

        Integer goodsId = 122;
        Integer carId = 123;

        Result<CenterGoodsCarDetailDTO> result = centerGoodsService.getGoodsCarDetailByGoodsCar(goodsId, carId);
        System.out.println(JsonUtil.objectToJsonStr(result));

        Result<List<CenterGoodsCarDTO>> listResult = centerGoodsService.getGoodsCarByGoodsCar(goodsId, null);
        System.out.println(JsonUtil.objectToJsonStr(listResult));

        Result<CenterGoodsCarSubjoinDTO> result1 = centerGoodsService.getSubjoinByGoodsModel(goodsId, 2);
        System.out.println(JsonUtil.objectToJsonStr(result1));

        System.out.println(" \n=======================================\n ");
    }

    @Test
    public void testA(){
        System.out.println(" \n=======================================\n ");

        String picNum = "505-005";
        Integer carId = 58850;

        Result<List<CenterGoodsCarDetailDTO>> result = centerGoodsService.getGcDetailByPicNumAndCar(picNum, carId);
        System.out.println(JsonUtil.objectToJsonStr(result));

        System.out.println(" \n=======================================\n ");
    }

}
