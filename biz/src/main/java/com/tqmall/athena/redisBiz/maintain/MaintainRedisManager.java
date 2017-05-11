package com.tqmall.athena.redisBiz.maintain;

import com.tqmall.athena.bean.entity.maintain.MaintainDetail;
import com.tqmall.athena.bean.entity.maintain.MaintainItemDO;

import java.util.List;

/**
 * Created by huangzhangting on 15/11/7.
 */
public interface MaintainRedisManager {
    /*
    *  查询所有保养项目
    * */
    List<MaintainItemDO> getItems();

    /*
    *  根据 车款id 查询保养里程
    * */
    List<Integer> getMiles(Integer carId);

    /*
    *  根据 车款id 查询保养细节
    *  carId：必填
    * */
    List<MaintainDetail> getMaintainDetail(Integer carId);

    /*
    *   查询年款保养方案
    * */
    List<MaintainDetail> getMaintainDetailByYearId(Integer yearId);

    /**
     * 根据车型分类id、级别，查询保养车款id（level 6）集合
     * @param carId
     * @param level
     * @return
     * @throws Exception
     */
    List<Integer> getMaintainCarIdsByCarId(Integer carId, Integer level);

    /**
     * 根据车型分类id、对应的保养车款id查询保养详情
     * @param carId
     * @param maintainCarId
     * @return
     * @throws Exception
     */
    List<MaintainDetail> getMaintainDetailCommon(Integer carId, Integer maintainCarId);

}
