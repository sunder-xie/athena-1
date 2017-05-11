package com.tqmall.athena.common.utils;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * Created by huangzhangting on 16/10/20.
 */
@Slf4j
public class PinyinUtil {

    public static String getFirstWord(String content){
        if(StringUtils.isEmpty(content)){
            return content;
        }
        try {
            return PinyinHelper.getShortPinyin(content.substring(0, 1)).toUpperCase();
        } catch (PinyinException e) {
            log.error("getShortPinyin error, content:"+content, e);
            return null;
        }
    }

}
