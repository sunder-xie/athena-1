package com.tqmall.athena.redisBiz.car;

import com.tqmall.athena.bean.bizBean.car.CarListBO;
import com.tqmall.athena.bean.entity.car.CarAllDO;
import com.tqmall.athena.bean.entity.car.CarCategoryDO;
import com.tqmall.athena.bean.entity.car.GoodsCarDO;

import java.util.List;

/**
 * Created by zxg on 15/9/22.
 * 车型缓存
 */
public interface CarRedisManager {
    
    /*========================================基本缓存=====================================================================*/
    //primaryId 得到online车型数据
    CarCategoryDO getCarByPrimaryId(Integer id);

    //根据pid 获得其子online Car数据
    List<CarCategoryDO> getCarListByPid(Integer pid);

    //根据力洋ID获得所有车型数据
    CarAllDO getCarAllByLiyangId(String liyangId);

    //根据goodsId获得所有对应车型数据list
    List<GoodsCarDO> getGoodsCarListByGoodsId(Integer goodsId);


    /*========================================二次加工的函数====================================================================*/

    //根据传入的pids 获得子online car list
    List<CarCategoryDO> getCarListByPid(List<Integer> pids);

    /**
     * 通过goodsId获得其对应的适配车型数据
     * 返回值做过处理，兼容老数据只返回4级
     */
    List<CarListBO> getCarForOldDataByPid(Integer pId);

    // =============== hzt 新增 =================
    /*
    *   根据level查询车型分类
    * */
    List<CarCategoryDO> getByLevel(Integer level);

    /*********************************20160309 lyj 新增接口 start*******************************************************/
    //根据唯一主键 ID 的集合 ids, 获得车型对象的集合
    List<CarCategoryDO> getByPrimaryIdList(List<Integer> ids);

    //获取记录id在otherModels中，brand不在brandNames中，且未删除的所有CarCategory
    List<CarCategoryDO> getByCarModels(List<Integer> otherModels, List<String> brandNames);

    //获取所有未删除CarCategory的 first_word，并去重（distinct first_word）
    List<String> getFirstWords();

    //根据firstWord 获取车型对象的集合
    List<CarCategoryDO> getByFirstWord(String firstWord);

    //根据level集合, 获取车型对象的集合
    List<CarCategoryDO> getByLevelList(List<Integer> levels);

    //根据firstWord 和 level, 获取车型对象的集合
    List<CarCategoryDO> getByFirstWordLevel(String firstWord, Integer level);
    /*********************************20160309 lyj 新增接口 end*********************************************************/
}
