package com.tqmall.athena.domain.result.carcategory;

import com.tqmall.athena.domain.util.ShareConstantsUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * copy from stall
 * 商品适配车型
 */
@Data
public class CarListSuit4GoodsDTO implements Serializable {

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
