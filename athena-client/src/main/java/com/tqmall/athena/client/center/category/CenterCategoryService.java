package com.tqmall.athena.client.center.category;

import com.tqmall.athena.domain.result.center.category.CenterCategoryShowDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by zxg on 16/10/26.
 * 16:49
 * no bug,以后改代码的哥们，祝你好运~！！
 * 分类service
 */
public interface CenterCategoryService {
    /*===基础service====*/

    /*===归类的service====*/
    /**
     * 根据展示的来源，获得展示的数据
     * @param showSource 展示来源，CenterCategoryShowDTO中的常量，不能为空
     * @param pid 展示分类父id，可为null，若为null或0，默认一级展示分类
     * @param vehicleType 分类类型，商用车还是乘用车-ShareConstants，可为null，若为null，默认全取
     * @return List
     */
    Result<List<CenterCategoryShowDTO>> getCategoryShowBySourcePid(Integer showSource, Integer pid,String vehicleType);
}
