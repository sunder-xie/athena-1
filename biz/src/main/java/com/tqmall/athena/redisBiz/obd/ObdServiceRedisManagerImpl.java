package com.tqmall.athena.redisBiz.obd;

import com.tqmall.athena.bean.entity.obd.ObdServiceDO;
import com.tqmall.athena.bussiness.obd.ObdServiceManager;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.obd.ObdServiceDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by zhouheng on 16/11/24.
 */
@Component
public class ObdServiceRedisManagerImpl implements ObdServiceRedisManager {

    @Autowired
    private RedisClientTemplate redisClient;

    @Autowired
    private ObdServiceDOMapper obdServiceDOMapper;


    @Override
    public List<ObdServiceDO> getObdListByObdNum(String obdNumber){

        String key = String.format(RedisKeyBean.OBD_SERVICE_BY_OBD_NUMBER,obdNumber);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return null;
        }
        if(redisStr != null){
            return JsonUtil.jsonStrToList(redisStr,ObdServiceDO.class);
        }

        List<ObdServiceDO> obdDOList = obdServiceDOMapper.queryObdDOListByObdNum(obdNumber);
        if(CollectionUtils.isEmpty(obdDOList)){
            redisClient.setNone(key);
        }else {
            redisClient.lazySet(key,obdDOList,RedisKeyBean.RREDIS_EXP_DAY);
        }

        return obdDOList;
    }



}
