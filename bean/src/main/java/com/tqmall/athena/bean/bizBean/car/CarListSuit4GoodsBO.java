package com.tqmall.athena.bean.bizBean.car;

import com.tqmall.athena.bean.common.ShareConstantsUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品适配车型
 * 对应client中的CarListSuit4GoodsDTO
 */
@Data
public class CarListSuit4GoodsBO implements Serializable {

    private String name;

    private String logo;

    private int count;

    private List<Model> models;

    private int totalCount;

    @Data
    public static class Model implements Serializable {

        private String value;

        private String company;

        private int count;

        private List<Displacement> displacements;

    }

    @Data
    public static class Displacement implements Serializable {

        private String value;

        private int count;

        private List<String> yearList;
    }

    public String getLogo() {
        return ShareConstantsUtil.getImgUrl(logo);
    }
}
