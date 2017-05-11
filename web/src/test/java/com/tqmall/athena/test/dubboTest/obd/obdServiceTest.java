package com.tqmall.athena.test.dubboTest.obd;

import com.tqmall.athena.bean.entity.obd.ObdServiceDO;
import com.tqmall.athena.bussiness.obd.ObdServiceManager;
import com.tqmall.athena.client.obd.ObdService;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.domain.result.obd.ObdServiceDTO;
import com.tqmall.athena.redisBiz.obd.ObdServiceRedisManager;
import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhouheng on 16/11/24.
 */
@Slf4j
public class obdServiceTest extends BaseDubboTest {

    @Autowired
    private ObdServiceManager obdServiceManager;

    @Autowired
    private ObdService obdService;

    /**
     * biz层测试
     */
    @Test
    public void getDataTest(){

       Result<List<ObdServiceDO>> result = obdServiceManager.getObdListByObdNum("P1295");

        log.info("*********"+ JsonUtil.objectToJsonStr(result));

    }

    /**
     * dubbo服务测试
     */
    @Test
    public void getObdService(){

        Result<List<ObdServiceDTO>> result = obdService.getObdListByObdNum("P1295");

        log.info("***123******"+ JsonUtil.objectToJsonStr(result));
    }

}
