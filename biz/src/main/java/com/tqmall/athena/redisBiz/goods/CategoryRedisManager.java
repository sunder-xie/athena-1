package com.tqmall.athena.redisBiz.goods;

import com.tqmall.athena.bean.bizBean.params.CategoryQueryParam;
import com.tqmall.athena.bean.entity.goods.CategoryBrandDO;
import com.tqmall.athena.bean.entity.goods.GoodsCategoryDO;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.goods.CategoryBrandDOMapper;
import com.tqmall.athena.dal.mapper.goods.GoodsCategoryDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhangting on 16/5/25.
 */
@Service
public class CategoryRedisManager {
    @Autowired
    private RedisClientTemplate redisClient;

    @Autowired
    private CategoryBrandDOMapper categoryBrandMapper;
    @Autowired
    private GoodsCategoryDOMapper categoryDOMapper;


    public List<CategoryBrandDO> getCateBrand(Integer cateId){
        String key = String.format(RedisKeyBean.GOODS_CATE_BRAND_KEY, cateId);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }
        if(redisStr!=null){
            return JsonUtil.jsonStrToList(redisStr, CategoryBrandDO.class);
        }
        List<CategoryBrandDO> list = categoryBrandMapper.selectCateBrand(cateId);
        if(list.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

    public List<GoodsCategoryDO> getByCondition(CategoryQueryParam param){
        String key = String.format(RedisKeyBean.CATEGORY_BY_CONDITION_KEY, param.getId(), param.getPid(),
                param.getLevel(), param.getVehicleCode(), param.getIsShow());
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }
        if(redisStr!=null){
            return JsonUtil.jsonStrToList(redisStr, GoodsCategoryDO.class);
        }
        List<GoodsCategoryDO> list = categoryDOMapper.selectByCondition(param);
        if(list.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

}
