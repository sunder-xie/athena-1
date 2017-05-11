package com.tqmall.athena.dal.mapper.car;

import com.tqmall.athena.bean.entity.car.CarAllDO;
import com.tqmall.athena.dal.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface CarAllDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarAllDO record);

    int insertSelective(CarAllDO record);

    CarAllDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarAllDO record);

    int updateByPrimaryKey(CarAllDO record);

    /*=======================================================*/
    //根据力洋Id获得对应的车型数据
    CarAllDO selectCarByLiyangId(@Param("newLId") String newLId);

}