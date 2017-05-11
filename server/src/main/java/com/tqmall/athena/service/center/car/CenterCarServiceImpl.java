package com.tqmall.athena.service.center.car;

import com.tqmall.athena.bean.entity.center.car.CenterCarDO;
import com.tqmall.athena.bussiness.center.car.CenterCarManager;
import com.tqmall.athena.client.center.car.CenterCarService;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.center.car.CenterCarDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangzhangting on 16/2/2.
 */
@Slf4j
public class CenterCarServiceImpl implements CenterCarService {
    @Autowired
    private CenterCarManager centerCarManager;

    @Override
    public Result<List<CenterCarDTO>> getCenterCarByPid(Integer pid) {
        Result<List<CenterCarDO>> result = centerCarManager.getCenterCarByPid(pid);
        return ResultUtil.handleResult4List(result, CenterCarDTO.class);
    }

    @Override
    public Result<List<CenterCarDTO>> getCenterCarByLevel(Integer level) {
        Result<List<CenterCarDO>> result = centerCarManager.getCenterCarByLevel(level);
        return ResultUtil.handleResult4List(result, CenterCarDTO.class);
    }

    @Override
    public Result<List<CenterCarDTO>> getCarListByModelId(Integer modelId) {
        Result<List<CenterCarDO>> result = centerCarManager.getCarListByModelId(modelId);
        return ResultUtil.handleResult4List(result, CenterCarDTO.class);
    }

    @Override
    public Result<CenterCarDTO> getCenterCarById(Integer id) {
        Result<CenterCarDO> result = centerCarManager.getCenterCarById(id);
        return ResultUtil.handleResult(result, CenterCarDTO.class);
    }
}
