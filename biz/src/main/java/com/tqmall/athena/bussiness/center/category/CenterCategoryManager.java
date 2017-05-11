package com.tqmall.athena.bussiness.center.category;

import com.tqmall.athena.bean.entity.center.category.CenterCategoryDO;
import com.tqmall.athena.bean.entity.center.category.CenterCategoryShowDO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by zxg on 16/10/26.
 * 19:25
 * no bug,以后改代码的哥们，祝你好运~！！
 */
public interface CenterCategoryManager {
    /**
     * 根据来源获得展示的分类
     * @param source 来源，不为null
     * @param pid 父id，
     * @param vehicleType 分类类型，商用车还是乘用车-ShareConstants，可为null
     * @return
     */
    Result<List<CenterCategoryShowDO>> getCategoryShowBySourcePid(Integer source, Integer pid,String vehicleType);
}
