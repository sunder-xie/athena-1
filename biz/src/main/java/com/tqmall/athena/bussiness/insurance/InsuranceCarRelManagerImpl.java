package com.tqmall.athena.bussiness.insurance;

import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.redisBiz.insurance.InsuranceRedisManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangzhangting on 16/9/19.
 */
@Slf4j
@Service
public class InsuranceCarRelManagerImpl implements InsuranceCarRelManager {
    @Autowired
    private InsuranceRedisManager insuranceRedisManager;

    @Override
    public Result<List<Integer>> getCarIds(String vehicleCode) {
        if(vehicleCode==null){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        String code = vehicleCode.replace(" ", "").toUpperCase();
        if("".equals(code)){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<Integer> list = insuranceRedisManager.getCarIds(code);
        if(list.isEmpty()){
            log.info("selectCarIds failed, vehicleCode:{}, code:{}", vehicleCode, code);
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }
        return ResultUtil.successResult(list);
    }
}
