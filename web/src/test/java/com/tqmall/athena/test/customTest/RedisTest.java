package com.tqmall.athena.test.customTest;

import com.tqmall.athena.common.Sha1Util;
import com.tqmall.athena.common.bean.HttpClientResult;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.HttpClientUtil;
import com.tqmall.athena.test.BaseDubboTest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by zxg on 15/9/20.
 */
public class RedisTest extends BaseDubboTest {

    @Autowired
    private RedisClientTemplate redisClientTemplate;

    @Test
    public void testRedis(){
        String pattern = "Athena_maintain_*";
        Set<String> keys = redisClientTemplate.getKeys(pattern);
        System.out.println("\n ================= keys =============== \n");
        System.out.println(keys);
        System.out.println(keys.toArray(new String[keys.size()])[0]);

        System.out.println("\n ================= keys =============== \n");
    }

    //删除redisKey，运行请慎重！！！！！！！！
    @Test
    public void testDelKey() throws ParseException {
        String redisKey = "Athena_car_category_list_by_pid_138";


        String url = "http://121.40.196.235:19211/redis/delKey";
        String header_key = "Tqmall.athena.ReDis/1";
        long time = System.currentTimeMillis();

        String oneKey = Sha1Util.getSha1(header_key + time);


        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("redisKey",redisKey));
        nvps.add(new BasicNameValuePair("time", time + ""));
        nvps.add(new BasicNameValuePair("compareKey", oneKey));

        HttpClientResult result = HttpClientUtil.postUrl(url, nvps);

        System.out.println(result.getResult());
    }

    //清除保养相关缓存，运行请慎重！！！！！！！！
    @Test
    public void testClearMaintain() throws ParseException {

        String url = "http://121.40.196.235:19211/redis/clearMaintain";
        String header_key = "Tqmall.athena.ReDis/1";
        long time = System.currentTimeMillis();

        String oneKey = Sha1Util.getSha1(header_key + time);

        List<NameValuePair> nvps = new ArrayList<>();

        nvps.add(new BasicNameValuePair("time", time + ""));
        nvps.add(new BasicNameValuePair("compareKey", oneKey));

        HttpClientResult result = HttpClientUtil.postUrl(url, nvps);

        System.out.println(result.getStatusCode());
        System.out.println(result.getResult());
    }

    //清除缓存，运行请慎重！！！！！！！！
    @Test
    public void batchClearRedis() throws Exception{
        List<String> list = new ArrayList<>();
        list.add("Athena_car_models_by_goods_id_*");
        list.add("Athena_car_series_by_goods_id_*");
//        list.add("Athena_cars_by_goods_*_model_*");


        //测试环境
        String url = "http://114.215.178.133:19211/redis/clearRedis";

        for (String str : list){
            testClearRedis(str, url);
        }
    }

    //@Test
    public void testClearRedis(String pattern, String url) throws ParseException {

        String header_key = "Tqmall.athena.ReDis/1";
        long time = System.currentTimeMillis();

        String oneKey = Sha1Util.getSha1(header_key + time);

        List<NameValuePair> nvps = new ArrayList<>();

        nvps.add(new BasicNameValuePair("time",time+""));
        nvps.add(new BasicNameValuePair("compareKey", oneKey));
        nvps.add(new BasicNameValuePair("pattern", pattern));

        HttpClientResult result = HttpClientUtil.postUrl(url, nvps);

        System.out.println(result.getStatusCode());
        System.out.println(result.getResult());
    }

}
