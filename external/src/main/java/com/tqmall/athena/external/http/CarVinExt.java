package com.tqmall.athena.external.http;

import com.tqmall.athena.common.bean.HttpClientResult;
import com.tqmall.athena.common.utils.HttpClientUtil;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.external.beans.VinServerResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhangting on 16/6/16.
 */
@Slf4j
@Component
public class CarVinExt {
    @Value("${vin.server.host}")
    private String vinServerHost;

    //访问力洋vin码接口，查询力洋id
    public List<String> getLyIdsFromLiYang(String vin){
        String url = vinServerHost + "/vin/searchLyIds/" + vin;
        HttpClientResult result = HttpClientUtil.getUrl(url);
        if(result==null){
            return new ArrayList<>();
        }
        String data = result.getResult();
        if(data==null){
            return new ArrayList<>();
        }
        VinServerResult vinServerResult = JsonUtil.jsonStrToObject(data, VinServerResult.class);
        if(vinServerResult==null || !vinServerResult.isSuccess()
                || CollectionUtils.isEmpty(vinServerResult.getData())){
            return new ArrayList<>();
        }

        return vinServerResult.getData();
    }
}
