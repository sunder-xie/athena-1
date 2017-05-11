package com.tqmall.athena.external.beans;

import lombok.Data;

/**
 * Created by huangzhangting on 16/9/19.
 */
@Data
public class SearchGoodsBO {
    private Integer id;
    private String goodsSn;
    private String goodsName;
    private String goodsFormat;
    private Integer brandId;
    private String brandName;
    private Integer catId;
    private String catName;

}
