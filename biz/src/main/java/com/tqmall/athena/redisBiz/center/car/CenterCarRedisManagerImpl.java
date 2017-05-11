package com.tqmall.athena.redisBiz.center.car;

import com.tqmall.athena.bean.entity.center.car.CenterCarDO;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.center.car.CenterCarDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhangting on 16/2/2.
 */
@Component
public class CenterCarRedisManagerImpl implements CenterCarRedisManager {
    @Autowired
    private RedisClientTemplate redisClient;

    @Autowired
    private CenterCarDOMapper centerCarDOMapper;

    @Override
    public List<CenterCarDO> getCenterCarByPid(Integer pid) {
        String key = String.format(RedisKeyBean.CENTER_CAR_PID_KEY, pid);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }

        if(redisStr != null)
            return JsonUtil.jsonStrToList(redisStr, CenterCarDO.class);

        List<CenterCarDO> list = centerCarDOMapper.selectByPid(pid);
        if(list.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

    @Override
    public List<CenterCarDO> getCenterCarByLevel(Integer level) {
        String key = String.format(RedisKeyBean.CENTER_CAR_LEVEL_KEY, level);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }

        if(redisStr != null)
            return JsonUtil.jsonStrToList(redisStr, CenterCarDO.class);

        List<CenterCarDO> list = centerCarDOMapper.selectByLevel(level);
        if(list.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

    @Override
    public List<CenterCarDO> getCarListByModelId(Integer modelId) {
        String key = String.format(RedisKeyBean.CENTER_CAR_MODEL_ID_KEY, modelId);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }

        if(redisStr != null)
            return JsonUtil.jsonStrToList(redisStr, CenterCarDO.class);

        List<CenterCarDO> list = centerCarDOMapper.selectCarListByModelId(modelId);
        if(list.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

    @Override
    public CenterCarDO getCenterCarById(Integer id) {
        String key = String.format(RedisKeyBean.CENTER_CAR_ID_KEY, id);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr))
            return null;

        if(redisStr != null)
            return JsonUtil.jsonStrToObject(redisStr, CenterCarDO.class);

        CenterCarDO carDO = centerCarDOMapper.selectByPrimaryKey(id);
        if(carDO==null){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, carDO, RedisKeyBean.RREDIS_EXP_WEEK);
        }
        return carDO;
    }
}
