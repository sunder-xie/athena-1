package com.tqmall.athena.dal.mapper.maintain;

import com.tqmall.athena.bean.entity.maintain.MaintainItemDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface MaintainItemDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MaintainItemDO record);

    int insertSelective(MaintainItemDO record);

    MaintainItemDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaintainItemDO record);

    int updateByPrimaryKey(MaintainItemDO record);

    List<MaintainItemDO> selectItems();

}