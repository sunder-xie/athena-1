package com.tqmall.athena.domain.result.carcategory;

import com.tqmall.athena.domain.util.ShareConstantsUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * copy from stall
 * Created by YangFalcom on 2014/7/23.
 */
@Data
public class HotCarBrandDTO implements Serializable {

    private Integer sort;

    private String status;

    private String logo;

    private String name;

    private String id;

    public String getLogo() {
        return ShareConstantsUtil.getImgUrl(logo);
    }
}
