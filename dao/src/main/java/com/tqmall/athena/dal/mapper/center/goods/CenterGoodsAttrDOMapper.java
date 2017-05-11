package com.tqmall.athena.dal.mapper.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsAttrDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CenterGoodsAttrDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CenterGoodsAttrDO record);

    CenterGoodsAttrDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CenterGoodsAttrDO record);

    /*===========*/
    List<CenterGoodsAttrDO> selectByAttrDO(CenterGoodsAttrDO record);
}