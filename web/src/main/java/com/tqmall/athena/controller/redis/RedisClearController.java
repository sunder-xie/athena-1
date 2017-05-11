package com.tqmall.athena.controller.redis;

import com.tqmall.athena.common.Sha1Util;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by zxg on 15/10/8.
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisClearController {
    private static final String header_key = "Tqmall.athena.ReDis/1";

    @Autowired
    private RedisClientTemplate redisClientTemplate;


    //测试方法
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result test(){
        return Result.wrapSuccessfulResult("test");
    }

    //删除key
    @RequestMapping(value = "/delKey", method = RequestMethod.POST)
    public Result delGoodsCar(String redisKey,long time, String compareKey){
        try {
            long now = System.currentTimeMillis();

            Long diff = now - time;
            //规定有效期为2个小时
            Long hour = diff / (1000 * 60 * 60);
            if (hour > 2) {
                return Result.wrapErrorResult("001", "超时,相隔时间过长");
            }
            //生成密钥比较
            String oneKey = Sha1Util.getSha1(header_key + time);
            if (!compareKey.equals(oneKey)) {
                return Result.wrapErrorResult("002", "密钥错误");
            }
            //删除key
            redisKey = redisKey.trim();
            redisClientTemplate.delKey(redisKey);

            return Result.wrapSuccessfulResult("success");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.wrapErrorResult("003",e.getMessage());
        }
    }

    //清除保养相关缓存
    @RequestMapping(value = "/clearMaintain", method = RequestMethod.POST)
    public Result clearMaintain(Long time, String compareKey){
        if(time==null || compareKey==null){
            return Result.wrapErrorResult("000", "参数不能为空");
        }
        try {
            long now = System.currentTimeMillis();

            Long diff = now - time;
            //规定有效期为2个小时
            Long hour = diff / (1000 * 60 * 60);
            if (hour > 2) {
                return Result.wrapErrorResult("001", "超时,相隔时间过长");
            }
            //生成密钥比较
            String oneKey = Sha1Util.getSha1(header_key + time);
            if (!oneKey.equals(compareKey)) {
                return Result.wrapErrorResult("002", "密钥错误");
            }
            //删除key
            String pattern = "Athena_maintain_*";
            Set<String> keys = redisClientTemplate.getKeys(pattern);
            if(keys.isEmpty()){
                return Result.wrapErrorResult("004", "没有保养相关缓存");
            }

            return Result.wrapSuccessfulResult(redisClientTemplate.delKeys(keys.toArray(new String[keys.size()])));

        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.wrapErrorResult("003",e.getMessage());
        }
    }

    //根据表达式清除缓存
    //例子：Athena_maintain_* ，也可以是具体值
    @RequestMapping(value = "/clearRedis", method = RequestMethod.POST)
    public Result clearRedis(Long time, String compareKey, String pattern){
        if(time==null || compareKey==null || pattern==null){
            return Result.wrapErrorResult("000", "参数不能为空");
        }
        if("".equals(pattern.trim())){
            return Result.wrapErrorResult("000", "参数不能为空");
        }

        try {
            long now = System.currentTimeMillis();

            Long diff = now - time;
            //规定有效期为2个小时
            Long hour = diff / (1000 * 60 * 60);
            if (hour > 2) {
                return Result.wrapErrorResult("001", "超时,相隔时间过长");
            }
            //生成密钥比较
            String oneKey = Sha1Util.getSha1(header_key + time);
            if (!oneKey.equals(compareKey)) {
                return Result.wrapErrorResult("002", "密钥错误");
            }
            //删除key
            Set<String> keys = redisClientTemplate.getKeys(pattern);
            if(keys.isEmpty()){
                return Result.wrapErrorResult("004", "没有相关缓存, pattern: "+pattern);
            }

            return Result.wrapSuccessfulResult(redisClientTemplate.delKeys(keys.toArray(new String[keys.size()])));

        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.wrapErrorResult("003",e.getMessage());
        }
    }

}
