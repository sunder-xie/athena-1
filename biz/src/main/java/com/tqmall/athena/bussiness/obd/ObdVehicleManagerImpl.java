package com.tqmall.athena.bussiness.obd;

import com.tqmall.athena.bean.entity.obd.ObdVehicleDO;
import com.tqmall.athena.bean.entity.obd.VehicleObdVehicleRelDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.dal.mapper.obd.VehicleObdVehicleRelDOMapper;
import com.tqmall.athena.redisBiz.obd.ObdVehicleRedisManager;
import com.tqmall.core.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by huangzhangting on 16/7/17.
 */
@Service
public class ObdVehicleManagerImpl implements ObdVehicleManager{
    @Autowired
    private RedisClientTemplate redisClient;
    @Autowired
    private ObdVehicleRedisManager obdVehicleRedisManager;
    @Autowired
    private VehicleObdVehicleRelDOMapper relDOMapper;


    @Override
    public Result<ObdVehicleDO> getObdVehicleByVehicleIdCode(Integer vehicleId, String vehicleCode) {
        if(vehicleId==null || vehicleId<1 || StringUtils.isEmpty(vehicleCode)){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        String key = String.format(RedisKeyBean.OBD_VEHICLE_BY_VEHICLE_ID_CODE, vehicleId, vehicleCode);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }
        if(redisStr != null){
            return ResultUtil.successResult(JsonUtil.jsonStrToObject(redisStr, ObdVehicleDO.class));
        }

        VehicleObdVehicleRelDO relDO = relDOMapper.selectByParam(vehicleId, vehicleCode);
        if(relDO != null){
            ObdVehicleDO obdVehicleDO = obdVehicleRedisManager.getObdVehicleDOById(relDO.getObdVehicleId());
            if(obdVehicleDO != null){
                redisClient.lazySet(key, obdVehicleDO, RedisKeyBean.RREDIS_EXP_DAY);
                return ResultUtil.successResult(obdVehicleDO);
            }
        }

        redisClient.setNone(key);
        return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
    }

    @Override
    public Result<ObdVehicleDO> getObdVehicleById(Integer id) {
        if(id==null || id<1){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        ObdVehicleDO obdVehicleDO = obdVehicleRedisManager.getObdVehicleDOById(id);
        if(obdVehicleDO==null){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        return ResultUtil.successResult(obdVehicleDO);
    }
}
