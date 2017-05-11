package com.tqmall.athena.dal.mapper.center.car;

import com.tqmall.athena.bean.entity.center.car.CenterCarDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CenterCarDOMapper {

    CenterCarDO selectByPrimaryKey(Integer id);

    List<CenterCarDO> selectByPid(Integer pid);

    List<CenterCarDO> selectByLevel(Integer level);

    /**
     * 根据车型id查车款集合（即：根据第三级id查询第六级数据）
     * @param modelId
     * @return
     */
    List<CenterCarDO> selectCarListByModelId(Integer modelId);

}