package com.tqmall.athena.redisBiz.center.category;

import com.tqmall.athena.bean.entity.center.category.CenterCategoryShowDO;

import java.util.List;

/**
 * Created by zxg on 16/10/26.
 * 19:33
 * no bug,以后改代码的哥们，祝你好运~！！
 */
public interface CenterCategoryRedisManager {
    /**
     * 根据来源获得展示分类列表
     * @param source 来源
     * @param pid 父id
     * @return
     */
    List<CenterCategoryShowDO> getCategoryShowBySourcePid(Integer source, Integer pid);
}
