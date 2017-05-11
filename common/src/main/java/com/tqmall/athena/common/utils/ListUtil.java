package com.tqmall.athena.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * list常用工具
 * Created by lyj on 16/4/25.
 */
public class ListUtil {
    /**
     * list 去重
     * @param li
     * @param <T>
     * @return
     */
    public static  <T> List<T> listDistinctUseContains(List<T> li) {
        List<T> list = new ArrayList<>();
        int len = li.size();
        for (int i = 0; i < len; i++) {
            T temp = li.get(i);
            if (!list.contains(temp)) {
                list.add(temp);
            }
        }
        return list;
    }
}
