package com.tqmall.athena.dal.mapper.car;

import com.tqmall.athena.bean.entity.car.CarCategoryDO;
import com.tqmall.athena.dal.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface CarCategoryDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarCategoryDO record);

    int insertSelective(CarCategoryDO record);

    CarCategoryDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarCategoryDO record);

    int updateByPrimaryKey(CarCategoryDO record);


    /*=============================*/

    //根据pid搜子级数据
    List<CarCategoryDO> selectByPid(@Param("pid") Integer pid);

    List<CarCategoryDO> selectByLevel(Integer level);

    List<CarCategoryDO> selectByPidList(@Param("pidList")List<Integer> pidList);

    //获取所有的FirstWords, 还没有去重
    List<String> selectFirstWords();

    //根据firstWord 获取车型对象的集合
    List<CarCategoryDO> selectByFirstWord(String firstWord);
}