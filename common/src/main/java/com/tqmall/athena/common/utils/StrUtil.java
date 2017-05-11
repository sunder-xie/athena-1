package com.tqmall.athena.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangzhangting on 16/3/2.
 */
public class StrUtil {
    /**
     * vin码验证
     * @param vin
     * @return
     */
    public static boolean isVin(String vin){
        if(vin==null){
            return false;
        }
        vin = vin.replace(" ","");
        if(vin.length()!=17){
            return false;
        }

        Pattern pattern = Pattern.compile("^[A-Z0-9]+$");
        Matcher matcher = pattern.matcher(vin.toUpperCase());

        return matcher.matches();
    }

}
