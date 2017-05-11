package com.tqmall.athena.domain.result.maintain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by huangzhangting on 15/9/21.
 */
@Data
public class MaintainDetailDTO implements Serializable {

    private Integer modelId;//车款id（电商车款 level=6）

    private Date firstTime;//新车上路时间

    private Integer mile;//里程

    private Integer serviceType;//服务类型(1:更换,2:检查,3:深度)

    private String serviceSuggest;//服务建议

    private String serviceUse;//用量

    private String timeConsuming;//服务工时

    private Double price;//服务花费

    private Integer serviceId;//服务项主键id

    private String serviceName;//服务项名称

    private String unit;//服务项单位

    private Integer sort;//服务项排序
}
