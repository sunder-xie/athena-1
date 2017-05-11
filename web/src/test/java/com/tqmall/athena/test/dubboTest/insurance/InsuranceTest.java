package com.tqmall.athena.test.dubboTest.insurance;

import com.tqmall.athena.client.insurance.CarGoodsService;
import com.tqmall.athena.client.insurance.InsuranceService;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.domain.result.insurance.InsuranceCompanyDTO;
import com.tqmall.athena.domain.result.insurance.RecommendOilDTO;
import com.tqmall.athena.external.beans.SearchGoodsBO;
import com.tqmall.athena.external.beans.SearchGoodsResult;
import com.tqmall.athena.external.http.GoodsSearchExt;
import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhangting on 15/11/27.
 */
@Slf4j
public class InsuranceTest extends BaseDubboTest {
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private CarGoodsService carGoodsService;
    @Autowired
    private GoodsSearchExt goodsSearchExt;


    @Test
    public void test() {
        //Result<List<InsuranceCompanyDTO>> result = insuranceService.getInsuranceCompany();
        //System.out.println(JsonUtil.objectToJsonStr(result));

//        String vin = "LDNH4LNE630001030";
//        String vin = "LFV5A44F3C3001079";
//        String vin = "WP1AC29P851A90851";
//        String vin = "1G1BL52P7TR11SS20";
        String vin = "LSGJA52H0ES260447";
        String carYear = "2013";

        long time1 = System.currentTimeMillis();
        Result<String> result1 = carGoodsService.recommendOilFilter(vin, carYear);

        long tim2 = System.currentTimeMillis();
        System.out.println("机滤推荐接口耗时："+(tim2-time1)+" ms");

        System.out.println(JsonUtil.objectToJsonStr(result1));

        List<String> list = new ArrayList<>();
        list.add("12344");
        list.add("12344123");
        list.add("1010200249");
        list.add("1010200260");

        long time3 = System.currentTimeMillis();
        Result<RecommendOilDTO> oilDTOResult = carGoodsService.recommendEngineOil(vin, carYear, list);
        long time4 = System.currentTimeMillis();
        System.out.println("机油推荐接口耗时："+(time4 - time3)+" ms");

        System.out.println(JsonUtil.objectToJsonStr(oilDTOResult));


//        String catIds = "4343";
//        String brandIds = "849";
//        Integer carId = 55725;
//        SearchGoodsResult goodsResult = goodsSearchExt.queryGoods(catIds, brandIds, carId, null, null);
//        System.out.println(JsonUtil.objectToJsonStr(goodsResult));

    }
}
