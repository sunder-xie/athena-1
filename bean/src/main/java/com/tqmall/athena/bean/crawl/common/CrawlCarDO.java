package com.tqmall.athena.bean.crawl.common;

import lombok.Data;

import java.util.Date;

@Data
public class CrawlCarDO {
    private Integer id;

    private Date gmtModified;

    private Date gmtCreate;

    private Integer source;

    private String carBrandName;

    private String carFactoryName;

    private String carFullName;

    private String carRemarks;

}