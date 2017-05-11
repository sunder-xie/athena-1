package com.tqmall.athena.redisBiz.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsAttrDO;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.center.goods.CenterGoodsAttrDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxg on 16/11/2.
 * 14:55
 * no bug,以后改代码的哥们，祝你好运~！！
 */
@Service
public class CenterGoodsAttrRedisManagerImpl implements CenterGoodsAttrRedisManager {
    @Autowired
    private RedisClientTemplate redisClient;

    @Autowired
    private CenterGoodsAttrDOMapper attrDOMapper;

    @Override
    public List<CenterGoodsAttrDO> getGoodsAttrByGoodsId(Integer goodsId) {
        String key = String.format(RedisKeyBean.CENTER_ATTR_LIST_BY_GOODS_ID,goodsId);
        String resultJson = redisClient.get(key);
        if(redisClient.isNone(resultJson)){
            return new ArrayList<>();
        }
        if(resultJson!=null){
            return JsonUtil.jsonStrToList(resultJson, CenterGoodsAttrDO.class);
        }
        //从数据库中取数据
        CenterGoodsAttrDO searchDO = new CenterGoodsAttrDO();
        searchDO.setGoodsId(goodsId);
        List<CenterGoodsAttrDO> attrDOList = attrDOMapper.selectByAttrDO(searchDO);
        if(CollectionUtils.isEmpty(attrDOList)){
            redisClient.setNone(key,RedisKeyBean.RREDIS_EXP_DAY);
            return new ArrayList<>();
        }

        redisClient.lazySet(key,attrDOList,RedisKeyBean.RREDIS_EXP_DAY);
        return attrDOList;
    }
}
