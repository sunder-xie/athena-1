package com.tqmall.athena.bean.common;


/**
 * athena 常量控制集
 * Created by 钟熙耿 on 15-09-20.
 */
public interface ShareConstants {

    String HTTP_IMG_URL = "http://img.tqmall.com/";

    //车辆的最小子级
    Integer CAR_FINAL_LEVEL = 6;

    //保养里程上线
    int MAX_MAINTAIN_MILE = 600000;

    //是否删除状态位
    String IS_DELETE = "Y";
    String NOT_DELETE = "N";

    //乘用车、商用车
    String PASSENGER_CAR = "C";//乘用车
    String COMMERCIAL_CAR = "H";//商用车

    //通过list参数查询数据, list的最大长度
    int QUERY_BY_LIST_MAX_SIZE = 50;

    Integer IS_SHOW = 1;
    Integer NOT_SHOW = 0;

    //商用车级别
    short BRAND_GRADE = 1;  // 车品牌
    short SERIES_GRADE = 2; // 车系
    short MODEL_GRADE = 3;  // 车型

    //in查询最大个数
    int IN_SIZE = 50;

    //生产环境
    String ENVIRONMENT_ONLINE = "online";

}