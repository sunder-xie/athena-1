package com.tqmall.athena.redisBiz.obd;

import com.tqmall.athena.bean.entity.obd.ObdServiceDO;

import java.util.List;

/**
 * Created by zhouheng on 16/11/24.
 */
public interface ObdServiceRedisManager {

    /**
     * 通过故障码获取故障码相关信息
     *
     * @param obdNumber
     * @return
     */
    List<ObdServiceDO> getObdListByObdNum(String obdNumber);

}
