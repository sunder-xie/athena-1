package com.tqmall.athena.redisBiz.insurance;

import com.tqmall.athena.bean.entity.insurance.InsuranceCompanyDO;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.insurance.InsuranceCarRelDOMapper;
import com.tqmall.athena.dal.mapper.insurance.InsuranceCompanyDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhangting on 15/11/27.
 */
@Component
public class InsuranceRedisManagerImpl implements InsuranceRedisManager{
    @Autowired
    private RedisClientTemplate redisClient;

    @Autowired
    private InsuranceCompanyDOMapper companyDOMapper;
    @Autowired
    private InsuranceCarRelDOMapper carRelDOMapper;


    @Override
    public List<InsuranceCompanyDO> getInsuranceCompany() {
        String key = RedisKeyBean.ALL_INSURANCE_COMPANY_KEY;
        List<InsuranceCompanyDO> list = redisClient.lazyGetList(key, InsuranceCompanyDO.class);
        if(CollectionUtils.isEmpty(list)){
            list = companyDOMapper.selectInsuranceCompany();
            if(!list.isEmpty()){
                redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
            }
        }
        return list;
    }

    @Override
    public List<Integer> getCarIds(String vehicleCode) {
        String key = String.format(RedisKeyBean.INSURANCE_CAR_IDS_KEY, vehicleCode);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return new ArrayList<>();
        }
        if(redisStr != null){
            return JsonUtil.jsonStrToList(redisStr, Integer.class);
        }
        List<Integer> list = carRelDOMapper.selectCarIds(vehicleCode);
        if(list.isEmpty()){
            redisClient.setNone(key, RedisKeyBean.RREDIS_EXP_FIVE_MINUTE);
        }else{
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_HOURS);
        }
        return list;
    }
}
