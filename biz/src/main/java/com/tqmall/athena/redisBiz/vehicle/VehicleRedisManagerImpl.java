package com.tqmall.athena.redisBiz.vehicle;

import com.tqmall.athena.bean.common.ShareConstants;
import com.tqmall.athena.bean.entity.vehicle.VehicleAttrValueDO;
import com.tqmall.athena.bean.entity.vehicle.VehicleCategoryDO;
import com.tqmall.athena.bean.entity.vehicle.VehicleDO;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.vehicle.VehicleAttrValueDOMapper;
import com.tqmall.athena.dal.mapper.vehicle.VehicleCategoryDOMapper;
import com.tqmall.athena.dal.mapper.vehicle.VehicleDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhangting on 16/3/23.
 */
@Component
public class VehicleRedisManagerImpl implements VehicleRedisManager {
    @Autowired
    private RedisClientTemplate redisClient;

    @Autowired
    private VehicleCategoryDOMapper vehicleCategoryDOMapper;
    @Autowired
    private VehicleDOMapper vehicleDOMapper;
    @Autowired
    private VehicleAttrValueDOMapper attrValueDOMapper;


    @Override
    public VehicleCategoryDO getVehicleCategoryById(Integer id) {
        String key = String.format(RedisKeyBean.VEHICLE_CATEGORY_ID_KEY, id);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return null;
        }
        if(redisStr!=null){
            return JsonUtil.jsonStrToObject(redisStr, VehicleCategoryDO.class);
        }

        VehicleCategoryDO categoryDO = vehicleCategoryDOMapper.selectByPrimaryKey(id);
        if(categoryDO==null){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, categoryDO, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return categoryDO;
    }

    @Override
    public List<VehicleCategoryDO> getVehicleCategoryByPid(Integer pid) {
        String key = String.format(RedisKeyBean.VEHICLE_CATEGORY_PID_KEY, pid);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }

        if(redisStr!=null){
            return JsonUtil.jsonStrToList(redisStr, VehicleCategoryDO.class);
        }

        List<VehicleCategoryDO> list = vehicleCategoryDOMapper.selectByPid(pid);
        if(list.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

    @Override
    public VehicleDO getVehicleDOById(Integer id) {
        String key = String.format(RedisKeyBean.VEHICLE_ID_KEY, id);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return null;
        }

        if(redisStr != null){
            return JsonUtil.jsonStrToObject(redisStr, VehicleDO.class);
        }

        VehicleDO vehicleDO = vehicleDOMapper.selectByPrimaryKey(id);
        if(vehicleDO == null){
            redisClient.setNone(key);
        }else{
            redisClient.setStringWithTime(key, JsonUtil.objectToJsonStr(vehicleDO), RedisKeyBean.RREDIS_EXP_DAY);
        }
        return vehicleDO;
    }

    @Override
    public List<VehicleDO> getVehicleDOByPid(Integer pid) {
        String key = String.format(RedisKeyBean.VEHICLE_LIST_PID_KEY, pid);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }

        if(redisStr != null){
            return JsonUtil.jsonStrToList(redisStr, VehicleDO.class);
        }

        List<VehicleDO> list = vehicleDOMapper.selectByPid(pid);
        if(list.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.setStringWithTime(key, JsonUtil.objectToJsonStr(list), RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }
    @Override
    public List<VehicleAttrValueDO> getAttrValueDOByVehicleId(Integer vehicleId) {
        String key = String.format(RedisKeyBean.ATTR_VALUE_VEHICLE_ID_KEY, vehicleId);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }

        if(redisStr != null){
            return JsonUtil.jsonStrToList(redisStr, VehicleAttrValueDO.class);
        }

        List<VehicleAttrValueDO> list = attrValueDOMapper.selectByVehicleId(vehicleId);
        if(list.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.setStringWithTime(key, JsonUtil.objectToJsonStr(list), RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

    @Override
    public List<VehicleAttrValueDO> getAttrValueDOByVehiclePid(Integer vehiclePid, List<Integer> vehicleIdList) {
        String key = String.format(RedisKeyBean.ATTR_VALUE_VEHICLE_PID_KEY, vehiclePid);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }

        if(redisStr != null){
            return JsonUtil.jsonStrToList(redisStr, VehicleAttrValueDO.class);
        }

        List<VehicleAttrValueDO> list = new ArrayList<>();
        int size = vehicleIdList.size();
        int limit = ShareConstants.IN_SIZE;
        if(size > limit){
            int s = size/limit; //商
            int y = size%limit; //余数
            List<Integer> subIds;
            for(int i=0; i<s; i++){
                int from = i*limit;
                subIds = vehicleIdList.subList(from, from+limit);
                list.addAll(attrValueDOMapper.selectByVehicleIdList(subIds));
            }
            if(y>0){
                subIds = vehicleIdList.subList(s*limit, size);
                list.addAll(attrValueDOMapper.selectByVehicleIdList(subIds));
            }
        }else{
            list = attrValueDOMapper.selectByVehicleIdList(vehicleIdList);
        }

        if(list.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.setStringWithTime(key, JsonUtil.objectToJsonStr(list), RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

    @Override
    public List<VehicleDO> getVehicleDOByNoticeNum(String noticeNum) {
        String key = String.format(RedisKeyBean.VEHICLE_NOTICE_NUM_KEY, noticeNum);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }
        if(redisStr != null){
            return JsonUtil.jsonStrToList(redisStr, VehicleDO.class);
        }

        List<VehicleDO> list = vehicleDOMapper.selectByNoticeNum(noticeNum);
        if(list.isEmpty()){
            redisClient.setNone(key);
        }else{
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }
}
