package com.tqmall.athena.domain.result.center.goods;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zxg on 16/11/2.
 * 14:41
 * no bug,以后改代码的哥们，祝你好运~！！
 */
@Data
public class CenterGoodsAttrDTO implements Serializable {

    private Integer goodsId;//goods_id

    private Integer attrId;//属性key的id

    private String attrName;//属性key的名称

    private String attrValue;//属性值
}
