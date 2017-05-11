package com.tqmall.athena.dal.mapper.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CenterGoodsCarDOMapper {

    CenterGoodsCarDO selectByPrimaryKey(Integer id);

    //根据do现有数据查询
    List<CenterGoodsCarDO> selectByDO(CenterGoodsCarDO record);
}