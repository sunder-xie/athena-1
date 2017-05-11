package com.tqmall.athena.test.customTest;

import com.tqmall.athena.common.Sha1Util;
import com.tqmall.athena.common.bean.HttpClientResult;
import com.tqmall.athena.common.utils.HttpClientUtil;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.core.common.entity.Result;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxg on 16/1/26.
 * 15:44
 */
public class MonitorAddTest {
    private final static String HEADER =  "Tqmall.monitor.Header./*";

    private static String onlineUrl = "http://dubbo.weichedao.com/monitor/rest/appPhone/saveAppPhone";

    private String url = onlineUrl;
    @Test
    public void addPhone(){
        addAppPhone("tqmallstall","18668087865");
    }


    private void addAppPhone(String appName,String phone){
        Long time = System.currentTimeMillis();
        //生成密钥比较
        String compareKey = Sha1Util.getSha1(HEADER + time);

        List<NameValuePair> nvps = new ArrayList<>();

        nvps.add(new BasicNameValuePair("appName",appName));
        nvps.add(new BasicNameValuePair("phone",phone));
        nvps.add(new BasicNameValuePair("time", String.valueOf(time)));
        nvps.add(new BasicNameValuePair("compareKey", compareKey));

        HttpClientResult result = HttpClientUtil.postUrl(url, nvps);

        System.out.println(JsonUtil.objectToJsonStr(result));
    }
}
