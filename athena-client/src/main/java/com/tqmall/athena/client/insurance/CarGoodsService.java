package com.tqmall.athena.client.insurance;

import com.tqmall.athena.domain.result.insurance.RecommendOilDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 16/9/18.
 */
public interface CarGoodsService {

    /**
     * 推荐机油
     * @param vin vin码
     * @param carYear 年款
     * @param goodsSnList 电商商品编码集合
     * @return 推荐的goodsSn 和 车型机油用量
     */
    Result<RecommendOilDTO> recommendEngineOil(String vin, String carYear, List<String> goodsSnList);

    /**
     * 推荐机油滤清器
     * @param vin vin码
     * @param carYear 年款
     * @return 推荐的goodsSn
     */
    Result<String> recommendOilFilter(String vin, String carYear);

}
