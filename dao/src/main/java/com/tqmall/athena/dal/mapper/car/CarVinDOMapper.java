package com.tqmall.athena.dal.mapper.car;

import com.tqmall.athena.bean.entity.car.CarVinDO;
import com.tqmall.athena.dal.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CarVinDOMapper {

    CarVinDO selectByPrimaryKey(Integer id);

    /**
     * 根据vin码查询力洋id集合，因为数据库有唯一约束，所以不会有重复的数据
     * @param vin
     * @return
     */
    List<String> selectLIdByVin(String vin);

}