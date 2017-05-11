package com.tqmall.athena.client.car;

import com.tqmall.athena.domain.result.carcategory.CarCategoryDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * 车型服务扩展接口
 * Created by huangzhangting on 16/3/24.
 */
public interface CarServiceExtend {
    /**
     * 根据pid查询车型分类，会在品牌级别组装上“其它”
     *
     * 备注：调用该接口获得的数据，不要去调用其他接口，组装上的数据会有问题
     * 例如：使用id，去调用 CarCategoryService里面的接口，
     *      拼接上的数据，即“其它”以及它下级的数据，是查不出结果的
     *
     * @param pid
     * @return
     */
    Result<List<CarCategoryDTO>> carListByPid(Integer pid);
}
