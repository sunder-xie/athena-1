package com.tqmall.athena.domain.result.center.goods;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * Created by huangzhangting on 16/2/17.
 */
@Data
public class CenterGoodsCarDetailDTO implements Serializable {
    private Integer id;

    private Integer goodsId;

    private Integer carId;

    private Integer modelId;

    private String goodsCarRemarks;

    private Integer applicationAmount;

    private String epcPicNum;

    private String epcIndex;

    private List<String> epcPicList;

    private String oeNumber;
    private String partName;

    //2016.10.26 新增
    private Integer brandId;//商品品牌id
    private String brandName;//商品品牌名称
    private String goodsFormat;//商品规格型号
    private String goodsPic;//商品图片
    private String attr_simple;//属性的拼接，逗号隔开



}
