package com.tqmall.athena.bean.bizBean.center;

import lombok.Data;

import java.util.List;

/**
 * Created by huangzhangting on 16/6/22.
 */
@Data
public class CenterGoodsCarDetailBO {
//    goods_car的唯一主键
    private Integer id;

    /*===goods_car====*/
    private Integer goodsId;

    private Integer carId;

    private Integer modelId;

    /*===subjoin====*/
    private String goodsCarRemarks;

    private Integer applicationAmount;

    /*===pic====*/
    private String epcPicNum;

    private String epcIndex;

    private List<String> epcPicList;

    /*===center_goods====*/
    private String oeNumber;
    private String partName;

    //2016.10.26 新增
    private Integer brandId;
    private String brandName;
    private String goodsFormat;//商品规格型号
    private String goodsPic;
    private String attr_simple;//属性的拼接，逗号隔开

}
