package com.tqmall.athena.common.redis;

/**
 * Created by zxg on 15/9/10.
 * redis key 的管理
 */

public interface RedisKeyBean {
    /*===========变量自动为 final static*/
    //redis变量key的系统前缀
    String SYSTEM_PREFIX = "Athena_";

    String NULL_OBJECT = "None";

    /*=============失效时间=======================================================================*/
    /**
     * 缓存时效 1分钟
     */
    int RREDIS_EXP_MINUTE = 60;

    int RREDIS_EXP_FIVE_MINUTE = 300;

    /**
     * 缓存时效 10分钟
     */
    int RREDIS_EXP_MINUTES = 600;

    /**
     * 缓存时效 60分钟
     */
    int RREDIS_EXP_HOURS = 3600;

    /**
     * 缓存时效 1天
     */
    int RREDIS_EXP_DAY = 3600 * 24;

    /**
     * 缓存时效 1周
     */
    int RREDIS_EXP_WEEK = 3600 * 24 * 7;

    /**
     * 缓存时效 1月
     */
    int RREDIS_EXP_MONTH = 3600 * 24 * 30 * 7;

    /*=====================自定义的各种key=========================================================================*/

    /**
     * ========car相关
     */
    // 缓存car的id对应的对象数据

    String CAR_CATEGORY_ID_KEY = SYSTEM_PREFIX+"car_category_id_key_%d";

    //pid下的所有子Ca value：list的json字符串
    String CAR_LIST_BY_PID_KEY = SYSTEM_PREFIX+"car_category_list_by_pid_%d";
    //力洋Id对应的online-线上carId
    String CAR_ALL_BY_LIYANG_KEY = SYSTEM_PREFIX+"car_all_by_liyang_%s";

    //6级车型拼接成四级车型
    String CAR_FOR_4_OLD_BY_PID = SYSTEM_PREFIX + "car_old_by_pid_%d";


    //key：商品id value：适配车型列表
    String CAR_LIST_BY_GOODS_KEY = SYSTEM_PREFIX+"car_goods_relation_list_by_goods_%d";

    String CAR_LIST_BY_LEVEL = SYSTEM_PREFIX+"car_category_level_%d";

    String CAR_FIRST_WORDS = SYSTEM_PREFIX+"car_first_words";

    String CAR_LIST_BY_FIRST_WORD = SYSTEM_PREFIX+"car_category_list_by_first_word_%s";

    String CAR_MODELS_BY_GOODS_ID = SYSTEM_PREFIX + "car_models_by_goods_id_%d";

    String CARS_BY_GOODS_MODEL_ID = SYSTEM_PREFIX + "cars_by_goods_%d_model_%d";

    String CAR_SERIES_BY_GOODS_ID = SYSTEM_PREFIX + "car_series_by_goods_id_%d";

    String CAR_MODELS_BY_GOODS_SERIES_ID = SYSTEM_PREFIX + "car_models_by_goods_%d_series_%d";

    /**
     * 保养相关
     */
    String MAINTAIN_ITEM_KEY = SYSTEM_PREFIX + "maintain_items";
    String MAINTAIN_MILE_PREFIX = SYSTEM_PREFIX + "maintain_mile_";
    String MAINTAIN_DETAIL_PREFIX = SYSTEM_PREFIX + "maintain_detail_%d";
    String MAINTAIN_DETAIL_CAR_ID = SYSTEM_PREFIX + "maintain_detail_car_id_%d";
    String MAINTAIN_DETAIL_YEAR_ID = SYSTEM_PREFIX + "maintain_detail_year_id_%d";
    String MAINTAIN_CAR_IDS_KEY = SYSTEM_PREFIX + "maintain_car_ids_%d";

    /**
     * 商品相关
     */
    String GOODS_IS_EXISTS_KEY = SYSTEM_PREFIX+"goods_is_exists_%d";

    /*
    *   商品类目相关
    * */
    String GOODS_CATE_BRAND_KEY = SYSTEM_PREFIX+"goods_cate_brand_%d";
    String CATEGORY_BY_CONDITION_KEY = SYSTEM_PREFIX+"cate_list_%d_%d_%d_%s_%d";


    /**
     * 保险相关
     */
    String ALL_INSURANCE_COMPANY_KEY = SYSTEM_PREFIX+"all_insurance_company";

    String INSURANCE_CAR_IDS_KEY = SYSTEM_PREFIX + "insurance_car_ids_%s";


    /**
     * 中心商品库相关
     */
    String CENTER_CAR_ID_KEY = SYSTEM_PREFIX + "center_car_id_%d";
    String CENTER_CAR_PID_KEY = SYSTEM_PREFIX + "center_car_pid_%d";
    String CENTER_CAR_LEVEL_KEY = SYSTEM_PREFIX + "center_car_level_%d";
    String CENTER_CAR_MODEL_ID_KEY = SYSTEM_PREFIX + "center_car_model_id_%d";
    //展示分类
    String CENTER_CATEGORY_BY_SOURCE_PID = SYSTEM_PREFIX + "center_category_source_%d_pid_%d";

    //车型下的分类
    String CENTER_CAR_CAT_KEY = SYSTEM_PREFIX + "center_car_cat_%d_vehicle_%s";

    //商品配件
    String CENTER_GOODS_PRIMARY_KEY = SYSTEM_PREFIX + "center_goods_primary_id_%d";
    String CENTER_GOODS_OE_KEY = SYSTEM_PREFIX + "center_goods_oe_%s";
    String CENTER_GOODS_CAT_KEY = SYSTEM_PREFIX + "center_goods_cat_id_%d";
    String CENTER_GOODS_PIC_NUM_CAR_KEY = SYSTEM_PREFIX + "center_goods_pic_num_%s_car_%d";

    //商品配件详情
    String CENTER_GC_DETAIL_GID_CID_KEY = SYSTEM_PREFIX + "center_gc_detail_gid_%d_cid_%d";
    String CENTER_GC_GID_CID_KEY = SYSTEM_PREFIX + "center_gc_gid_%d_cid_%d";
    String CENTER_GC_PIC_ID_KEY = SYSTEM_PREFIX + "center_gc_pic_id_%d";
    String CENTER_GC_SUB_ID_KEY = SYSTEM_PREFIX + "center_gc_sub_id_%d";
    String CENTER_GC_SUB_GID_MID_KEY = SYSTEM_PREFIX + "center_gc_sub_gid_%d_mid_%d";
    String CENTER_GCP_PIC_NUM_KEY = SYSTEM_PREFIX + "center_gcp_pic_num_%s";
    String CENTER_GC_DETAIL_PIC_NUM_CAR_KEY = SYSTEM_PREFIX + "center_gc_detail_pic_num_%s_car_%d";
    //车型+分类，获得goods详情
    String CENTER_GC_DETAIL_CATE_CAR_KEY = SYSTEM_PREFIX + "center_gc_detail_cate_%d_car_%d";
    //goodsId,下的属性列表
    String CENTER_ATTR_LIST_BY_GOODS_ID = SYSTEM_PREFIX + "center_attr_list_goods_%d";

    /**
     *  商用车相关
     */
    //车型
    String VEHICLE_ID_KEY = SYSTEM_PREFIX + "vehicle_id_key_%d";
    String VEHICLE_LIST_PID_KEY = SYSTEM_PREFIX + "vehicle_list_pid_key_%d";
    String VEHICLE_LIST_GRADE_KEY = SYSTEM_PREFIX + "vehicle_list_grade_key_%d";
    String VEHICLE_NOTICE_NUM_KEY = SYSTEM_PREFIX + "vehicle_notice_num_%s";
    //扩展属性相关
    String ATTR_VALUE_VEHICLE_ID_KEY = SYSTEM_PREFIX + "attr_value_vehicle_id_key_%d";
    String ATTR_VALUE_VEHICLE_PID_KEY = SYSTEM_PREFIX + "attr_value_vehicle_pid_key_%d";

    //车辆分类
    String VEHICLE_CATEGORY_PID_KEY = SYSTEM_PREFIX + "vehicle_category_pid_%d";
    String VEHICLE_CATEGORY_ID_KEY = SYSTEM_PREFIX + "vehicle_category_id_%d";


    //bank相关key
    String BANK_ALL_BANK_NAME = SYSTEM_PREFIX + "bank_all_bank_name";
    String BANK_BMP_BY_CONDITION = SYSTEM_PREFIX + "bank_bmp_kw_%s_pId_%d_cId_%d_dId_%d";
    String BANK_BMP_CITY_BY_CONDITION = SYSTEM_PREFIX + "bank_city_kw_%s_pId_%d";
    String BANK_BMP_DISTRICT_BY_CONDITION = SYSTEM_PREFIX + "bank_district_kw_%s_pId_%d_cId_%d";

    //vin码查询相关
    String CAR_LIST_BY_VIN = SYSTEM_PREFIX+"car_list_by_vin_%s";
    String VIN_LIMIT_TODAY = SYSTEM_PREFIX+"vin_limit_%s";



    /**
     * obd车型相关key
     */
    String OBD_VEHICLE_BY_VEHICLE_ID_CODE = SYSTEM_PREFIX + "obd_vehicle_by_vehicle_id_%d_code_%s";
    String OBD_VEHICLE_BY_ID = SYSTEM_PREFIX + "obd_vehicle_by_id_%d";

    /**
     * obd故障码服务相关
     */
    String OBD_SERVICE_BY_OBD_NUMBER = SYSTEM_PREFIX + "obd_service_by_obd_number_%s";

}
