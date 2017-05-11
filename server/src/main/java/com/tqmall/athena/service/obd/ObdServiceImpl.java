package com.tqmall.athena.service.obd;

import com.tqmall.athena.bean.entity.obd.ObdServiceDO;
import com.tqmall.athena.bussiness.obd.ObdServiceManager;
import com.tqmall.athena.client.obd.ObdService;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.obd.ObdServiceDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhouheng on 16/11/24.
 */
@Slf4j
public class ObdServiceImpl implements ObdService{

    @Autowired
    private ObdServiceManager obdServiceManager;

    @Override
    public Result<List<ObdServiceDTO>> getObdListByObdNum(String obdNumber) {
        Result<List<ObdServiceDO>> result = obdServiceManager.getObdListByObdNum(obdNumber);
        return ResultUtil.handleResult4List(result,ObdServiceDTO.class);
    }

}
