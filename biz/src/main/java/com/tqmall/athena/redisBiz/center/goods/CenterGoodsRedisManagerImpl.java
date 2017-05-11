package com.tqmall.athena.redisBiz.center.goods;

import com.google.common.collect.Lists;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarDO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarPictureDO;
import com.tqmall.athena.bean.entity.center.goods.CenterGoodsDO;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.center.goods.CenterGoodsDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by huangzhangting on 16/2/17.
 */
@Component
public class CenterGoodsRedisManagerImpl implements CenterGoodsRedisManager {
    @Autowired
    private RedisClientTemplate redisClient;

    @Autowired
    private CenterGoodsDOMapper goodsDOMapper;
    @Autowired
    private CenterGoodsCarRedisManager goodsCarRedisManager;


    @Override
    public CenterGoodsDO getGoodsByPrimaryId(Integer goodsId) {
        String key = String.format(RedisKeyBean.CENTER_GOODS_PRIMARY_KEY, goodsId);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr))
            return null;

        if(redisStr != null)
            return JsonUtil.jsonStrToObject(redisStr, CenterGoodsDO.class);

        CenterGoodsDO goodsDO = goodsDOMapper.selectByPrimaryKey(goodsId);
        if(goodsDO==null){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, goodsDO, RedisKeyBean.RREDIS_EXP_DAY);
        }

        return goodsDO;
    }

    @Override
    public CenterGoodsDO getGoodsByOeNumber(String oeNumber) {
        String key = String.format(RedisKeyBean.CENTER_GOODS_OE_KEY, oeNumber);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr))
            return null;

        if(redisStr != null)
            return JsonUtil.jsonStrToObject(redisStr, CenterGoodsDO.class);

        CenterGoodsDO goodsDO = goodsDOMapper.selectByOeNumber(oeNumber);
        if(goodsDO==null){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, goodsDO, RedisKeyBean.RREDIS_EXP_DAY);
        }

        return goodsDO;
    }

    @Override
    public List<CenterGoodsDO> getGoodsByThirdCatId(Integer thirdCateId) {
        String key = String.format(RedisKeyBean.CENTER_GOODS_CAT_KEY, thirdCateId);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr))
            return Lists.newArrayList();

        if(redisStr != null)
            return JsonUtil.jsonStrToList(redisStr, CenterGoodsDO.class);

        CenterGoodsDO searchDO = new CenterGoodsDO();
        searchDO.setThirdCateId(thirdCateId);
        List<CenterGoodsDO> goodsDOList = goodsDOMapper.selectByDO(searchDO);
        if(goodsDOList.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, goodsDOList, RedisKeyBean.RREDIS_EXP_DAY);
        }

        return goodsDOList;
    }

    @Override
    public List<CenterGoodsDO> getGoodsByPicNumAndCar(String picNum, Integer carId) {
        String key = String.format(RedisKeyBean.CENTER_GOODS_PIC_NUM_CAR_KEY, picNum, carId);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }
        if(redisStr!=null){
            return JsonUtil.jsonStrToList(redisStr, CenterGoodsDO.class);
        }

        List<CenterGoodsCarPictureDO> pictureDOList = goodsCarRedisManager.getPictureByPicNum(picNum);
        if(pictureDOList.isEmpty()){
            redisClient.setNone(key);
            return new ArrayList<>();
        }

        List<CenterGoodsCarDO> goodsCarDOList = goodsCarRedisManager.getGoodsCarByGoodsCar(null, carId);
        if(goodsCarDOList.isEmpty()){
            redisClient.setNone(key);
            return new ArrayList<>();
        }

        Set<Integer> goodsIdSet = new HashSet<>();
        for(CenterGoodsCarPictureDO pictureDO : pictureDOList){
            for(CenterGoodsCarDO goodsCarDO : goodsCarDOList){
                if(pictureDO.getId().equals(goodsCarDO.getPicId())){
                    goodsIdSet.add(goodsCarDO.getGoodsId());
                }
            }
        }

        List<CenterGoodsDO> goodsDOList = new ArrayList<>();
        for(Integer goodsId : goodsIdSet){
            CenterGoodsDO goodsDO = getGoodsByPrimaryId(goodsId);
            if(goodsDO==null){
                continue;
            }
            goodsDOList.add(goodsDO);
        }

        if(goodsDOList.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, goodsDOList, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return goodsDOList;
    }
}
