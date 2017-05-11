package com.tqmall.athena.dal.mapper.car;

import com.tqmall.athena.bean.entity.car.CarCategoryDO;
import com.tqmall.athena.bean.entity.car.GoodsCarDO;
import com.tqmall.athena.dal.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@MyBatisRepository
public interface GoodsCarDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsCarDO record);

    int insertSelective(GoodsCarDO record);

    GoodsCarDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsCarDO record);

    int updateByPrimaryKey(GoodsCarDO record);


    //=============================================
    List<GoodsCarDO> selectListByGoodsId(Integer goodsId);

    List<CarCategoryDO> getCarModelByGoodsId(Integer goodsId);

    List<GoodsCarDO> getCarByGoodsModelId(@Param("goodsId")Integer goodsId, @Param("carModelId")Integer carModelId);

    List<CarCategoryDO> getCarSeriesByGoodsId(Integer goodsId);

    List<GoodsCarDO> getCarModelByGoodsSeriesId(@Param("goodsId")Integer goodsId, @Param("carSeriesId")Integer carSeriesId);

    Set<Integer> getCarIdsByGoodsId(Integer goodsId);

    Set<Integer> getInvalidCarIdsByGoodsId(Integer goodsId);

    /**
     * 无效数据状态改为有效
     * @param goodsId
     * @param carIds
     * @return
     */
    int updateInvalidDatas(@Param("goodsId")Integer goodsId, @Param("carIds")Set<Integer> carIds);

    int batchInsert(List<GoodsCarDO> list);
}