package com.tqmall.athena.bussiness.insurance;

import com.tqmall.athena.bean.bizBean.insurance.RecommendOilBO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 16/9/19.
 */
public interface CarGoodsManager {

    /**
     * 推荐机油
     * @param vehicleCode 安心车型编码
     * @param goodsSnList 电商商品编码集合
     * @return 推荐的goodsSn
     */
    @Deprecated
    Result<String> recommendEngineOil(String vehicleCode, List<String> goodsSnList);

    /**
     * 推荐机油滤清器
     * @param vehicleCode 安心车型编码
     * @return 推荐的goodsSn
     */
    @Deprecated
    Result<String> recommendOilFilter(String vehicleCode);



    /** ========== 业务调整，代码重新写 ========== */

    /**
     * 推荐机油
     * @param vin vin码
     * @param carYear 年款
     * @param goodsSnList 电商商品编码集合
     * @return 推荐的goodsSn 和 车型机油用量
     */
    Result<RecommendOilBO> recommendEngineOil(String vin, String carYear, List<String> goodsSnList);

    /**
     * 推荐机油滤清器
     * @param vin vin码
     * @param carYear 年款
     * @return 推荐的goodsSn
     */
    Result<String> recommendOilFilter(String vin, String carYear);

}
