package com.tqmall.athena.dal.mapper.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarSubjoinDO;
import com.tqmall.athena.dal.MyBatisRepository;

@MyBatisRepository
public interface CenterGoodsCarSubjoinDOMapper {

    CenterGoodsCarSubjoinDO selectByPrimaryKey(Integer id);

}