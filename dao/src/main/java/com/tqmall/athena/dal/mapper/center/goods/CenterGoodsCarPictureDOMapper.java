package com.tqmall.athena.dal.mapper.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsCarPictureDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CenterGoodsCarPictureDOMapper {

    CenterGoodsCarPictureDO selectByPrimaryKey(Integer id);

    List<CenterGoodsCarPictureDO> selectByPicNum(String picNum);
}