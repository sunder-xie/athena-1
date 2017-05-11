package com.tqmall.athena.redisBiz.bank;

import com.tqmall.athena.bean.bizBean.bank.BankTqAreaBO;
import com.tqmall.athena.bean.entity.bank.BankBmpDO;
import com.tqmall.athena.bean.entity.bank.BankKeywordDO;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.common.utils.ListUtil;
import com.tqmall.athena.dal.mapper.bank.BankBmpDOMapper;
import com.tqmall.athena.dal.mapper.bank.BankKeywordDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyj on 16/4/25.
 */
@Component
public class BankRedisManagerImpl implements BankRedisManager {
    @Autowired
    private RedisClientTemplate redisClient;

    @Autowired
    private BankKeywordDOMapper bankKeywordDOMapper;

    @Autowired
    private BankBmpDOMapper bankBmpDOMapper;

    @Override
    public List<BankKeywordDO> getAllBankName() {
        //清所有缓存
//        for (String key: redisClient.getKeys("Athena_bank_*")){
//            redisClient.delKey(key);
//        }

        String key = String.format(RedisKeyBean.BANK_ALL_BANK_NAME);
        String redisStr = redisClient.get(key);
        if (redisClient.isNone(redisStr)) {
            return new ArrayList<>();
        }

        if (redisStr != null) {
            return JsonUtil.jsonStrToList(redisStr, BankKeywordDO.class);
        }

        List<BankKeywordDO> list = bankKeywordDOMapper.selectAll();
        if (list.isEmpty()) {
            redisClient.setNone(key);
        } else {
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

    @Override
    public List<BankBmpDO> getBankBmp(String keyword, Integer provinceId, Integer cityId, Integer districtId) {
        String key = String.format(RedisKeyBean.BANK_BMP_BY_CONDITION, keyword, provinceId, cityId, districtId);
        String redisStr = redisClient.get(key);
        if (redisClient.isNone(redisStr)) {
            return new ArrayList<>();
        }

        if (redisStr != null) {
            return JsonUtil.jsonStrToList(redisStr, BankBmpDO.class);
        }

        List<BankBmpDO> list = bankBmpDOMapper.selectBankBmp(keyword, provinceId, cityId, districtId);
        if (list.isEmpty()) {
            redisClient.setNone(key);
        } else {
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }

        return list;
    }

    @Override
    public List<BankTqAreaBO> getBmpCity(String keyword, Integer provinceId) {
        String key = String.format(RedisKeyBean.BANK_BMP_CITY_BY_CONDITION, keyword, provinceId);
        String redisStr = redisClient.get(key);
        if (redisClient.isNone(redisStr)) {
            return new ArrayList<>();
        }

        if (redisStr != null) {
            return JsonUtil.jsonStrToList(redisStr, BankTqAreaBO.class);
        }

        List<BankTqAreaBO> list = bankBmpDOMapper.selectBmpCity(keyword, provinceId);
        //去重
        List<BankTqAreaBO> listDistinct = ListUtil.listDistinctUseContains(list);
        if (list.isEmpty()) {
            redisClient.setNone(key);
        } else {
            redisClient.lazySet(key, listDistinct, RedisKeyBean.RREDIS_EXP_DAY);
        }

        return listDistinct;
    }

    @Override
    public List<BankTqAreaBO> getBmpDistrict(String keyword, Integer provinceId, Integer cityId) {
        String key = String.format(RedisKeyBean.BANK_BMP_DISTRICT_BY_CONDITION, keyword, provinceId, cityId);
        String redisStr = redisClient.get(key);
        if (redisClient.isNone(redisStr)) {
            return new ArrayList<>();
        }

        if (redisStr != null) {
            return JsonUtil.jsonStrToList(redisStr, BankTqAreaBO.class);
        }

        List<BankTqAreaBO> list = bankBmpDOMapper.selectBmpDistrict(keyword, provinceId, cityId);
        //去重
        List<BankTqAreaBO> listDistinct = ListUtil.listDistinctUseContains(list);
        if (list.isEmpty()) {
            redisClient.setNone(key);
        } else {
            redisClient.lazySet(key, listDistinct, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return listDistinct;
    }
}
