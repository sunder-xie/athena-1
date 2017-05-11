package com.tqmall.athena.common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * do 和 bo 的互相转化类
 */
public class BdUtil {


    public static <DO, BO> DO do2bo(BO request, Class<DO> cls) {
        if (null == request) return null;
        DO result;
        try {
            result = cls.newInstance();
            BeanUtils.copyProperties(request, result);
        } catch (Exception e) {
            throw new IllegalArgumentException("对象copy失败，请检查相关module", e);
        }
        return result;
    }

    public static <DO, BO> List<DO> do2bo4List(Collection<BO> request, Class<DO> cls) {
        List<DO> result = Lists.newArrayList();
        for (BO obj : request) {
            result.add(do2bo(obj, cls));
        }
        return result;
    }

    public static <DO, BO, K> Map<K, DO> bo2do4Map(Map<K, BO> request, Class<DO> cls) {
        Map<K, DO> result = Maps.newHashMap();
        for (Map.Entry<K, BO> item : request.entrySet()) {
            K key = item.getKey();
            BO val = item.getValue();
            result.put(key, do2bo(val, cls));
        }
        return result;
    }

}
