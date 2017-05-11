package com.tqmall.athena.dal.mapper.center.goods;

import com.tqmall.athena.bean.entity.center.goods.CenterGoodsDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CenterGoodsDOMapper {

    CenterGoodsDO selectByPrimaryKey(Integer id);

    CenterGoodsDO selectByOeNumber(String oeNumber);

    List<CenterGoodsDO> selectByDO(CenterGoodsDO record);
}