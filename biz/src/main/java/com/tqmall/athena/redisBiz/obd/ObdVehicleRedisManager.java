package com.tqmall.athena.redisBiz.obd;

import com.tqmall.athena.bean.entity.obd.ObdVehicleDO;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.obd.ObdVehicleDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by huangzhangting on 16/7/17.
 */
@Component
public class ObdVehicleRedisManager {
    @Autowired
    private RedisClientTemplate redisClient;
    @Autowired
    private ObdVehicleDOMapper obdVehicleDOMapper;

    public ObdVehicleDO getObdVehicleDOById(Integer id){
        String key = String.format(RedisKeyBean.OBD_VEHICLE_BY_ID, id);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return null;
        }
        if(redisStr != null){
            return JsonUtil.jsonStrToObject(redisStr, ObdVehicleDO.class);
        }
        ObdVehicleDO obdVehicleDO = obdVehicleDOMapper.selectByPrimaryKey(id);
        if(obdVehicleDO==null){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, obdVehicleDO, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return obdVehicleDO;
    }

}
