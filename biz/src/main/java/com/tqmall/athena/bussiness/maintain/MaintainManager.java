package com.tqmall.athena.bussiness.maintain;

import com.tqmall.athena.bean.entity.maintain.AdviseMaintainBO;
import com.tqmall.athena.bean.entity.maintain.MaintainDetail;
import com.tqmall.athena.bean.entity.maintain.MaintainItemDO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by huangzhangting on 15/9/20.
 */
public interface MaintainManager {

    /*
    *  查询所有保养项目
    * */
    List<MaintainItemDO> findItems();

    /*
    *  根据 车款id 查询保养里程
    * */
    List<Integer> findMiles(Integer carId);

    /*
    *  根据 车款id、输入里程 查询保养细节
    *  carId：必填
    *  mile：选填
    * */
    List<MaintainDetail> findMaintainDetail(Integer carId, Integer mile);


    /*
    *   查询年款保养方案
    * */
    List<MaintainDetail> findMaintainDetailByYearId(Integer yearId);

    /*
    *  根据 年款id、输入里程
    *  yearId：必填
    *  mile：必填
    * */
    AdviseMaintainBO adviseMaintainByYearId(Integer yearId, Integer mile);

    /*
    *  根据 车型分类id、输入里程 查询保养细节
    *  carId：必填
    *  mile：选填
    * */
    Result<List<MaintainDetail>> findMaintainDetailCommon(Integer carId, Integer mile);
}
