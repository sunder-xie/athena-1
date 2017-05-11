package com.tqmall.athena.bean.entity.center.category;

import lombok.Data;

import java.util.Date;

//展示的分类，在三级分类的基础上归类得到的
@Data
public class CenterCategoryShowDO {
    public static final Integer PORTAL_SHOWSOURCE = 0;//电商映射分类
    public static final Integer EPC_H5_SHOWSOURCE = 1;//H5配件查询系统分类

    private Integer id;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer creator;

    private Integer modifier;

    private Integer parentId;

    private String catNameMapping;

    private Integer catId;

    private String catName;

    private String categoryThumb;

    private String categoryImg;

    private Integer catMappingLevel;

    private Integer sortOrder;

    private String vehicleCode;

    private String productLine;

    private Integer showSource;

    public static Boolean isInShowSource(Integer source) {
        return source != null && (source.equals(PORTAL_SHOWSOURCE) || source.equals(EPC_H5_SHOWSOURCE));
    }
}