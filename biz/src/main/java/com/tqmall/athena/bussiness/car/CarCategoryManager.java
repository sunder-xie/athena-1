package com.tqmall.athena.bussiness.car;

import com.tqmall.athena.bean.bizBean.car.CarListBO;
import com.tqmall.athena.bean.bizBean.car.CarListSuit4GoodsBO;
import com.tqmall.athena.bean.entity.car.CarCategoryDO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by zxg on 15/9/21.
 * 车型相关逻辑
 */
public interface CarCategoryManager {
    /**
     * 根据唯一主键获得车型对象
     * @param carId
     * @return
     */
    CarCategoryDO getCarCategoryByPrimaryId(Integer carId);

    /**
     * 根据pId获取子车型数据
     * 数据为原生态车型数据,无加工
     * @param pid 父id，当pid=0时，取所有品牌
     * @return
     */
    List<CarCategoryDO> getCarCategoryByPid(Integer pid);


    /**
     * 根据车型id(carId)获取车型数据
     * 传出该车型id对应的父级、父级的父级等等直到1级所对应的所有pid构成的List对象
     * @param carId
     * @return List<CarCategoryDO>
     */
    List<CarCategoryDO> getCarParentsByCarId(Integer carId);

    /**
     * 力洋Id对应的车型Id
     * @param newLId 力洋Id
     * @return 线上车型数据 若不存在则为null
     */
    CarCategoryDO getCarCatByLId(String newLId);



    /**
     * 根据pId获取车型数据
     * 返回值做过处理，兼容老数据只返回4级
     * @param pId
     * @return
     */
    List<CarListBO> getCarForOldDataByPid(Integer pId);

    /**
     * 通过goodsId获得其对应的适配车型数据
     * @param goodsId 线上商品Id
     * @return 所有层级的适配车型
     */
    List<CarListSuit4GoodsBO> getCartListByGoodsId(Integer goodsId);


    // ==================== hzt 新增 ====================
    /*
    *   根据level查询车型分类
    * */
    Result<List<CarCategoryDO>> findByLevel(Integer level);

    /*
    *   根据父级id集合查询车型分类
    * */
    Result<List<CarCategoryDO>> findByPidList(List<Integer> pidList);

    /*********************************20160309 lyj 新增接口 start*******************************************************/
    /**
     * 根据唯一主键id 集合, 获取匹配的车型数据
     * @param ids
     * @return
     */
    Result<List<CarCategoryDO>> findByPrimaryIdList(List<Integer> ids);

    /**
     * 获取记录id在otherModels中，brand不在brandNames中，且未删除的所有CarCategory
     * @param otherModels
     * @param brandNames
     * @return
     */
    Result<List<CarCategoryDO>> findByCarModels(List<Integer> otherModels, List<String> brandNames);

    /**
     * 获取所有的 first_word, 已去重, 不包含空值, 无排序。
     * @return
     */
    Result<List<String>> findFirstWords();

    /**
     * 根据firstWord 获取车型对象的集合
     * @param firstWord
     * @return
     */
    Result<List<CarCategoryDO>> findByFirstWord(String firstWord);

    /**
     * 根据level集合, 获取车型对象的集合
     * @param levels
     * @return
     */
    Result<List<CarCategoryDO>> findByLevelList(List<Integer> levels);

    /**
     * 根据firstWord 和 level, 获取车型对象的集合
     * @param firstWord
     * @param level
     * @return
     */
    Result<List<CarCategoryDO>> findByFirstWordLevel(String firstWord, Integer level);
    /*********************************20160309 lyj 新增接口 end*********************************************************/
}
