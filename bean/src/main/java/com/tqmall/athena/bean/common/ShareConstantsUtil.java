package com.tqmall.athena.bean.common;

/**
 * Created by zxg on 16/11/24.
 * 11:00
 * no bug,以后改代码的哥们，祝你好运~！！
 */
public class ShareConstantsUtil {

    public static String getImgUrl(String imgUrl){
        if (null != imgUrl) {
            imgUrl = imgUrl.trim();

            if(!imgUrl.equals("")) {
                if (imgUrl.startsWith("http:")) {
//                    imgUrl = imgUrl.replace("http:", "https:");
                } else if (imgUrl.startsWith("https")) {
                    //不做任何操作
                } else {
                    imgUrl = ShareConstants.HTTP_IMG_URL + imgUrl;
                }
            }
        }
        return imgUrl;
    }
}
