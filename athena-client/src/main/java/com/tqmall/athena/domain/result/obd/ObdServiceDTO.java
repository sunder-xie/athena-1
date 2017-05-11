package com.tqmall.athena.domain.result.obd;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhouheng on 16/11/24.
 */
@Data
public class ObdServiceDTO implements Serializable {


    private Integer id;

    private String obdNumber;

    private String obdApplication;

    private String obdSystem;

    private String obdZhDefinition;

    private String obdEnDefinition;

    private String obdBackground;

}
