package com.tqmall.athena.bussiness.center.car;

import com.tqmall.athena.bean.entity.center.car.CenterCarDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.redisBiz.center.car.CenterCarRedisManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangzhangting on 16/2/2.
 */
@Slf4j
@Service
public class CenterCarManagerImpl implements CenterCarManager {
    @Autowired
    private CenterCarRedisManager centerCarRedisManager;

    @Override
    public Result<List<CenterCarDO>> getCenterCarByPid(Integer pid) {
        if(pid==null || pid<0)
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        List<CenterCarDO> list = centerCarRedisManager.getCenterCarByPid(pid);
        if(list.isEmpty())
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        return ResultUtil.successResult(list);
    }

    @Override
    public Result<List<CenterCarDO>> getCenterCarByLevel(Integer level) {
        if(level==null || level<1)
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        List<CenterCarDO> list = centerCarRedisManager.getCenterCarByLevel(level);
        if(list.isEmpty())
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        return ResultUtil.successResult(list);
    }

    @Override
    public Result<List<CenterCarDO>> getCarListByModelId(Integer modelId) {
        if(modelId==null || modelId<1)
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        List<CenterCarDO> list = centerCarRedisManager.getCarListByModelId(modelId);
        if(list.isEmpty())
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        return ResultUtil.successResult(list);
    }

    @Override
    public Result<CenterCarDO> getCenterCarById(Integer id) {
        if(id==null || id<1)
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        CenterCarDO carDO = centerCarRedisManager.getCenterCarById(id);
        if(carDO==null)
            return ResultUtil.errorResult(DataError.ARG_ERROR);

        return ResultUtil.successResult(carDO);
    }
}
