package com.tqmall.athena.client.obd;

import com.tqmall.athena.domain.result.obd.ObdServiceDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * 故障码查询dubbo服务
 * Created by zhouheng on 16/11/24.
 */
public interface ObdService{

    /**
     * 通过故障码获取故障码信息列
     *
     * @param obdNumber
     * @return
     */
    Result<List<ObdServiceDTO>> getObdListByObdNum(String obdNumber);

}
