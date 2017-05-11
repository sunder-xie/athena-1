package com.tqmall.athena.external.vin;

import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.core.common.entity.Result;
import com.tqmall.data.vin.client.VinCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhangting on 17/3/20.
 */
@Slf4j
@Service
public class ExtVinService {
    @Autowired
    private VinCodeService vinCodeService;

    public List<String> getLyIdsFromLiYang(String vin){
        Result<List<String>> result = vinCodeService.getLyIds(vin);
        if(result.isSuccess()){
            return result.getData();
        }
        log.info("get li yang ids failed, vin:{}, result:{}", vin, JsonUtil.objectToJsonStr(result));
        return new ArrayList<>();
    }
}
