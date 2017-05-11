package com.tqmall.athena.dal.mapper.maintain;

import com.tqmall.athena.bean.entity.maintain.MaintainDetail;
import com.tqmall.athena.bean.entity.maintain.ModelMaintainRelationDO;
import com.tqmall.athena.dal.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ModelMaintainRelationDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModelMaintainRelationDO record);

    int insertSelective(ModelMaintainRelationDO record);

    ModelMaintainRelationDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModelMaintainRelationDO record);

    int updateByPrimaryKey(ModelMaintainRelationDO record);

    List<ModelMaintainRelationDO> selectRelations(@Param("carId")Integer carId, @Param("mile")Integer mile);

    List<Integer> selectMiles(Integer carId);

    List<Integer> selectCloseMiles(@Param("carId")Integer carId, @Param("mile")Integer mile);

    int checkMile(@Param("carId")Integer carId, @Param("mile")Integer mile);

    List<MaintainDetail> selectMaintainDetail(@Param("carId")Integer carId, @Param("mile")Integer mile);

    List<Integer> selectCarIdsByYearId(Integer carId);

    List<Integer> selectCarIdsByPowerId(Integer carId);

    List<Integer> selectCarIdsByModelId(Integer carId);

    List<Integer> selectCarIdsBySeriesId(Integer carId);

    List<Integer> selectCarIdsByBrandId(Integer carId);
}