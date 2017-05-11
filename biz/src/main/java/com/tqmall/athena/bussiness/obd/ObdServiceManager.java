package com.tqmall.athena.bussiness.obd;

import com.tqmall.athena.bean.entity.obd.ObdServiceDO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by zhouheng on 16/11/24.
 */
public interface ObdServiceManager {

    /**
     * 通过故障码查询数据信息
     * @param obdNumber
     * @return
     */
    Result<List<ObdServiceDO>> getObdListByObdNum(String obdNumber);

}
