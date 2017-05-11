package com.tqmall.athena.bean.entity.center.goods;

import com.tqmall.athena.bean.common.ShareConstantsUtil;
import lombok.Data;

import java.util.Date;

@Data
public class CenterGoodsDO {
    private Integer id;

    private String isDeleted;

    private Date gmtModified;

    private Date gmtCreate;

    private String creator;

    private String modifier;

    private String oeNumber;

    private Integer brandId;

    private String goodsFormat;

    private String goodsPic;

    private String goodsUnit;

    private Integer thirdCateId;

    private Integer partId;

    private String partName;

    private String brandName;

    public String getGoodsPic() {
        return ShareConstantsUtil.getImgUrl(goodsPic);
    }

}