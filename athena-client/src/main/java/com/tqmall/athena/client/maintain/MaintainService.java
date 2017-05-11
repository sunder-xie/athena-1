package com.tqmall.athena.client.maintain;

import com.tqmall.athena.domain.param.maintain.CarMaintainPO;
import com.tqmall.athena.domain.result.maintain.AdviseMaintainDTO;
import com.tqmall.athena.domain.result.maintain.ItemDTO;
import com.tqmall.athena.domain.result.maintain.MaintainDetailDTO;
import com.tqmall.athena.domain.result.maintain.MaintainItemDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 15/9/21.
 */
public interface MaintainService {
    /*
    *  根据 车款id 查询保养里程
    *  carId：必填，（level 6级别的car id）
    * */
    Result<List<Integer>> getMiles(Integer carId);

    /*
    * （老的）查询保养项：查的是跟车相关的保养项目，不会查出通用项目
    * 只会查出 机油，机油滤清器，空气滤清器，空调滤清器，燃油滤清器，火花塞，俗称小保养项目
    * */
    Result<List<ItemDTO>> getItems();

    /*
    *  根据 车款id、输入里程 查询保养详情
    *  carId：必填，（level 6级别的car id）
    *  mile：选填，（ null：查询所有里程 ）
    * */
    Result<List<MaintainDetailDTO>> getMaintainDetail(Integer carId, Integer mile);

    /*
    *   根据 年款id、里程数 查询推荐保养
    *
    * */
    Result<AdviseMaintainDTO> getAdviseMaintain(CarMaintainPO param);

    /*
    *  根据 车型分类id、输入里程 查询保养详情
    *  carId：必填，（随便哪一级别的car id）
    *  mile：选填，（ null：查询所有里程 ）
    * */
    Result<List<MaintainDetailDTO>> maintainDetailCommon(Integer carId, Integer mile);


    /*
    * ========================================= 20160307 =========================================
    * 以上接口针对老的数据结构写的，并且有调用方，继续使用老的逻辑，暂时不作调整，即：不会查询新增的保养项目
    * 新接口需求，建议重新开发接口
    * */

    /*
    * 查询所有保养项目
    * */
    Result<List<MaintainItemDTO>> allMaintainItems();

    /*
    *  根据 车型分类id、输入里程 查询下次保养里程
    *  carId：必填，（随便哪一级别的car id）
    *  mileage：必填
    * */
    Result<Integer> nextMaintainMileage(Integer carId, Integer mileage);

}
