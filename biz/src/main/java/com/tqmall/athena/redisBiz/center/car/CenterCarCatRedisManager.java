package com.tqmall.athena.redisBiz.center.car;

import com.google.common.collect.Lists;
import com.tqmall.athena.bean.entity.center.car.CenterCarCategoryDO;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.center.car.CenterCarCategoryDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zxg on 16/2/3.
 * 14:47
 */
@Component
public class CenterCarCatRedisManager {

    @Autowired
    private RedisClientTemplate redisClientTemplate;
    @Autowired
    private CenterCarCategoryDOMapper centerCarCategoryDOMapper;

    /*根据车型id获得适配的分类*/
    public List<CenterCarCategoryDO> getListByCarIdVehicle(Integer carId,String vehicleCode){

        String key = String.format(RedisKeyBean.CENTER_CAR_CAT_KEY, carId,vehicleCode);
        String redisStr = redisClientTemplate.get(key);
        if(redisClientTemplate.isNone(redisStr))
            return Lists.newArrayList();

        if(redisStr != null)
            return JsonUtil.jsonStrToList(redisStr, CenterCarCategoryDO.class);

        CenterCarCategoryDO searchDO = new CenterCarCategoryDO();
        searchDO.setCarId(carId);
        searchDO.setVehicleCode(vehicleCode);
        List<CenterCarCategoryDO> list = centerCarCategoryDOMapper.selectListByDO(searchDO);
        if(list.isEmpty()){
            redisClientTemplate.setNone(key);
        }else{
            redisClientTemplate.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;

    }

}
