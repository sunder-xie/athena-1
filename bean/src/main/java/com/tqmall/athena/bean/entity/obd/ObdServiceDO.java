package com.tqmall.athena.bean.entity.obd;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhouheng on 16/11/24.
 */
@Data
public class ObdServiceDO {

    private Integer id;

    private String obdNumber;

    private String obdApplication;

    private String obdSystem;

    private String obdZhDefinition;

    private String obdEnDefinition;

    private String obdBackground;


}
