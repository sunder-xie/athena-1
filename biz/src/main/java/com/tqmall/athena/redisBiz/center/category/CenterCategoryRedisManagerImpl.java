package com.tqmall.athena.redisBiz.center.category;

import com.tqmall.athena.bean.entity.center.category.CenterCategoryShowDO;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.center.category.CenterCategoryShowDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxg on 16/10/26.
 * 19:48
 * no bug,以后改代码的哥们，祝你好运~！！
 */
@Service
public class CenterCategoryRedisManagerImpl implements CenterCategoryRedisManager {

    @Autowired
    private RedisClientTemplate redisClient;
    @Autowired
    private CenterCategoryShowDOMapper centerCategoryShowDOMapper;

    @Override
    public List<CenterCategoryShowDO> getCategoryShowBySourcePid(Integer source, Integer pid) {
        String key = String.format(RedisKeyBean.CENTER_CATEGORY_BY_SOURCE_PID,source, pid);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }

        if(redisStr != null)
            return JsonUtil.jsonStrToList(redisStr, CenterCategoryShowDO.class);

        CenterCategoryShowDO searchDO = new CenterCategoryShowDO();
        searchDO.setShowSource(source);
        searchDO.setParentId(pid);

        List<CenterCategoryShowDO> list = centerCategoryShowDOMapper.selectByShowDO(searchDO);
        if(list.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }
}
