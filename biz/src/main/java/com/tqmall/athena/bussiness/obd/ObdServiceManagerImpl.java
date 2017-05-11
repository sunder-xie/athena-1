package com.tqmall.athena.bussiness.obd;

import com.tqmall.athena.bean.entity.obd.ObdServiceDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.redisBiz.obd.ObdServiceRedisManager;
import com.tqmall.core.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by zhouheng on 16/11/24.
 */
@Service
public class ObdServiceManagerImpl implements ObdServiceManager{

    @Autowired
    private ObdServiceRedisManager obdServiceRedisManager;

    @Override
    public Result<List<ObdServiceDO>> getObdListByObdNum(String obdNumber) {
        if(StringUtils.isEmpty(obdNumber)){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<ObdServiceDO> serviceDOList = obdServiceRedisManager.getObdListByObdNum(obdNumber);
        if(CollectionUtils.isEmpty(serviceDOList)){
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }

        return Result.wrapSuccessfulResult(serviceDOList);
    }


}
