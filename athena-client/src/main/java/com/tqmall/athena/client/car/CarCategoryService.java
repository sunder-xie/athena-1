package com.tqmall.athena.client.car;


import com.tqmall.athena.domain.result.carcategory.CarCategoryDTO;
import com.tqmall.athena.domain.result.carcategory.CarListDTO;
import com.tqmall.athena.domain.result.carcategory.CarListSuit4GoodsDTO;
import com.tqmall.athena.domain.result.carcategory.HotCarBrandDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by zxg on 2015-09-21.
 */
public interface CarCategoryService {
    /*********************************1.0.0 start*************************************************************************************/
    /**
     * 获得所有品牌的车型数据，即pid=0的车型数据
     */
    Result<List<CarCategoryDTO>> getAllCarCategory();

    /**
     * 根据pId获取子车型数据
     * 数据为原生态车型数据,无加工
     * @param pid 父id，当pid=0时，取所有品牌
     */
    Result<List<CarCategoryDTO>> getCarCategoryByPid(Integer pid);


    /**
     * 根据车型id(carId)获取车型数据
     * 传出该车型id对应的父级、父级的父级等等直到1级所对应的所有pid构成的List对象
     * @param carId
     * @return List<CarCategoryDTO>
     */
    Result<List<CarCategoryDTO>> getCarParentsByCarId(Integer carId);

    /**
     * 力洋Id对应的车型Id
     * @param newLId 力洋Id
     * @return 线上车型数据
     */
    Result<CarCategoryDTO> queryCarCatInfoByLId(String newLId);



    /**
     * 根据pId获取车型数据
     * 返回值做过处理，兼容老数据只返回4级
     * @param pId
     * @return
     */
    Result<List<CarListDTO>> categoryCarInfo(Integer pId);

    /**
     * 通过goodsId获得其对应的适配车型数据
     * @param goodsId 线上商品Id
     * @return 所有层级的适配车型
     */
    Result<List<CarListSuit4GoodsDTO>> getCartListByGoodsId(Integer goodsId);



    //=========特殊：封装Iserver的接口===================================
    Result<List<HotCarBrandDTO>> getHotCarBrandList(Integer cityId);

    //=========新增的接口=====================================

    /**
     * 根据唯一主键id获得单一的车型对象
     * @param carId 车型的唯一主键：id
     * @return
     */
    Result<CarCategoryDTO> getCarCategoryByPrimaryId(Integer carId);


    /*********************************1.0.0 end************************************************************************************/


    /*
    *   根据level查询
    * */
    Result<List<CarCategoryDTO>> getByLevel(Integer level);

    /*
    *   根据父级id集合查询
    * */
    Result<List<CarCategoryDTO>> getByPidList(List<Integer> pidList);


    /*********************************20160309 lyj 新增接口 start*******************************************************/
    /**
     * 根据唯一主键 ID 的集合 ids, 获得车型对象的集合
     * @param ids
     * @return
     */
    Result<List<CarCategoryDTO>> getByPrimaryIdList(List<Integer> ids);

    /**
     * 获取记录id在otherModels中，brand不在brandNames中，且未删除的所有CarCategory
     * @param otherModels
     * @param brandNames
     * @return
     */
    Result<List<CarCategoryDTO>> getByCarModels(List<Integer> otherModels, List<String> brandNames);

    /**
     * 获取所有的 first_word, 已去重, 不包含空值, 无排序。
     * @return
     */
    Result<List<String>> getFirstWords();

    /**
     * 根据firstWord 获取车型对象的集合
     * @param firstWord
     * @return
     */
    Result<List<CarCategoryDTO>> getByFirstWord(String firstWord);

    /**
     * 根据level集合, 获取车型对象的集合
     * @param levels
     * @return
     */
    Result<List<CarCategoryDTO>> getByLevelList(List<Integer> levels);

    /**
     * 根据firstWord 和 level, 获取车型对象的集合
     * @param firstWord
     * @param level
     * @return
     */
    Result<List<CarCategoryDTO>> getByFirstWordLevel(String firstWord, Integer level);
    /*********************************20160309 lyj 新增接口 end*********************************************************/

}
