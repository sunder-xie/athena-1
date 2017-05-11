package com.tqmall.athena.test.dubboTest.goods;

import com.google.common.collect.Lists;
import com.tqmall.athena.bean.entity.car.GoodsCarDO;
import com.tqmall.athena.bussiness.car.GoodsCarManager;
import com.tqmall.athena.client.goods.GoodsService;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.car.GoodsCarDOMapper;
import com.tqmall.athena.domain.result.goods.GoodsCarDTO;
import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.core.common.entity.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangzhangting on 15/10/22.
 */
public class GoodsTest extends BaseDubboTest {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsCarDOMapper goodsCarDOMapper;


    @Test
    public void test(){
        System.out.println("\n======================================\n");
        Integer goodsId = 19957;
        Result<List<GoodsCarDTO>> result = goodsService.suitableCarModels(goodsId);
        System.out.println(JsonUtil.objectToJsonStr(result));

        Integer carModelId = 75294;
        result = goodsService.suitableCars(goodsId, carModelId);
        System.out.println(JsonUtil.objectToJsonStr(result));

        result = goodsService.suitableCarSeries(goodsId);
        System.out.println(JsonUtil.objectToJsonStr(result));

        Integer carSeriesId = 350;
        result = goodsService.suitableCarModelsBySeriesId(goodsId, carSeriesId);
        System.out.println(JsonUtil.objectToJsonStr(result));

        System.out.println("\n======================================\n");
    }

    @Test
    public void test_copyGoodsCar(){
        Integer destGoodsId = 571249;
        Integer srcGoodsId = 387956;//24570;//326462;
        Result<Boolean> result = goodsService.copyGoodsCar(destGoodsId, srcGoodsId);
        System.out.println(JsonUtil.objectToJsonStr(result));
    }

    @Test
    public void test_query(){
        Integer goodsId = 571248;
        List<GoodsCarDO> list = goodsCarDOMapper.selectListByGoodsId(goodsId);
        System.out.println(JsonUtil.objectToJsonStr(list));
    }

    @Test
    public void test_11(){
        List<Integer> list = Lists.newArrayList(1,2,3,5,6,7,8,9,0,5,6,7);
        List<Integer> subList = list.subList(3, list.size());
        System.out.println(subList);
    }
}
