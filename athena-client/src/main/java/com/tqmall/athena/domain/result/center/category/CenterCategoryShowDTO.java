package com.tqmall.athena.domain.result.center.category;

import com.tqmall.athena.domain.util.ShareConstantsUtil;
import lombok.Data;

import java.io.Serializable;

//展示的分类，在三级分类的基础上归类得到的
@Data
public class CenterCategoryShowDTO implements Serializable {
    public static final Integer PORTAL_SHOWSOURCE = 0;//电商映射分类
    public static final Integer EPC_H5_SHOWSOURCE = 1;//H5配件查询系统分类

    private Integer id;
    //父id
    private Integer parentId;
    //展示分类名称
    private String catNameMapping;
    //实际三级分类id
    private Integer catId;
    //实际三级分类名称
    private String catName;
    //展示分类的缩略图
    private String categoryThumb;
    //展示分类的原图
    private String categoryImg;
    //展示分类的层级
    private Integer catMappingLevel;
    //排序标识
    private Integer sortOrder;
    //车辆种类码：C-乘用车 H-商用车
    private String vehicleCode;
    //产品线
    private String productLine;
    //展示来源
    private Integer showSource;

    public String getCategoryThumb(){
        return ShareConstantsUtil.getImgUrl(categoryThumb);
    }

    public String getCategoryImg(){
        return ShareConstantsUtil.getImgUrl(categoryImg);
    }
}