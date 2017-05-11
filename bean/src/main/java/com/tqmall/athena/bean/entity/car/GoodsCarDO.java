package com.tqmall.athena.bean.entity.car;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * created by 钟熙耿 on 2015-09-22
 * 商品车型对应关系
 */

@Data
public class GoodsCarDO implements Serializable {
    private Integer id;

    private Integer goodsId;

    private Integer carId;

    private String carName;

    private Integer level;

    private Integer carBrandId;

    private String carBrand;

    private Integer carSeriesId;

    private String carSeries;

    private Integer carModelId;

    private String carModel;

    private Integer carPowerId;

    private String carPower;

    private Integer carYearId;

    private String carYear;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;


}