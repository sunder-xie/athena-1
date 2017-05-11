package com.tqmall.athena.test.dubboTest.goods;

import com.tqmall.athena.client.goods.GoodsCategoryService;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.domain.param.CategoryQueryPO;
import com.tqmall.athena.domain.result.goods.CategoryDTO;
import com.tqmall.athena.domain.result.goods.GoodsCateBrandDTO;
import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.core.common.entity.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangzhangting on 15/10/22.
 */
public class GoodsCategoryTest extends BaseDubboTest {
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Test
    public void test(){
        System.out.println("\n======================================\n");

//        Integer cateId = 4191;
//        Result<List<GoodsCateBrandDTO>> result = goodsCategoryService.getCateBrands(cateId);
//        System.out.println(JsonUtil.objectToJsonStr(result));

        CategoryQueryPO queryPO = new CategoryQueryPO();
        queryPO.setPid(0);
        queryPO.setVehicleCode("H");
        queryPO.setIsShow(1);

//        queryPO.setId(50242);

        Result<List<CategoryDTO>> result1 = goodsCategoryService.getByCondition(queryPO);
        System.out.println(JsonUtil.objectToJsonStr(result1));

        result1 = goodsCategoryService.getCommercialCateByPid(50014);
        System.out.println(JsonUtil.objectToJsonStr(result1));

        Result<CategoryDTO> result = goodsCategoryService.getCategoryById(50014);
        System.out.println(JsonUtil.objectToJsonStr(result));


        System.out.println("\n======================================\n");
    }

}
