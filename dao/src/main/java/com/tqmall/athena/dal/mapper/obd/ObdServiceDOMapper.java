package com.tqmall.athena.dal.mapper.obd;

import com.tqmall.athena.bean.entity.obd.ObdServiceDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

/**
 * Created by zhouheng on 16/11/24.
 */
@MyBatisRepository
public interface ObdServiceDOMapper {

    /**
     * 通过故障码获取故障码数据
     *
     * @param obdNumber
     * @return
     */
    List<ObdServiceDO> queryObdDOListByObdNum(String obdNumber);
}
