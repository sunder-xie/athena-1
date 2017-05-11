package com.tqmall.athena.service.car;

import com.tqmall.athena.bean.bizBean.car.CarListBO;
import com.tqmall.athena.bean.bizBean.car.CarListSuit4GoodsBO;
import com.tqmall.athena.bean.entity.car.CarCategoryDO;
import com.tqmall.athena.bussiness.car.CarCategoryManager;
import com.tqmall.athena.client.car.CarCategoryService;
import com.tqmall.athena.common.utils.BdUtil;
import com.tqmall.athena.domain.result.carcategory.CarCategoryDTO;
import com.tqmall.athena.domain.result.carcategory.CarListDTO;
import com.tqmall.athena.domain.result.carcategory.CarListSuit4GoodsDTO;
import com.tqmall.athena.domain.result.carcategory.HotCarBrandDTO;
import com.tqmall.athena.external.car.CarConsumeService;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxg on 15/9/21.
 */
@Slf4j
public class CarCategoryServiceImpl implements CarCategoryService {

    @Autowired
    private CarCategoryManager carCategoryManager;

    @Autowired
    private CarConsumeService carConsumeService;

    @Override
    public Result<List<CarCategoryDTO>> getAllCarCategory() {
        Integer pid = 0;
        return this.getCarCategoryByPid(pid);
    }

    @Override
    public Result<List<CarCategoryDTO>> getCarCategoryByPid(Integer pid) {
        if (pid == null || pid < 0) {
            return Result.wrapErrorResult(null, "参数不能小于0或等于null!");
        }
        List<CarCategoryDTO> list = BdUtil.do2bo4List(carCategoryManager.getCarCategoryByPid(pid), CarCategoryDTO.class);

        return Result.wrapSuccessfulResult(list);
    }

    @Override
    public Result<List<CarCategoryDTO>> getCarParentsByCarId(Integer carId) {
        if (carId == null) {
            return Result.wrapErrorResult("0", "传入的车型id为空～");
        }
        List<CarCategoryDTO> resultList = BdUtil.do2bo4List(carCategoryManager.getCarParentsByCarId(carId), CarCategoryDTO.class);

        return Result.wrapSuccessfulResult(resultList);
    }

    @Override
    public Result<CarCategoryDTO> queryCarCatInfoByLId(String newLId) {
        if (null == newLId || "".equals(newLId.trim())) {
            return Result.wrapErrorResult("0", "传入的新力洋id为空哇～");
        }
        CarCategoryDO carCategoryDO = carCategoryManager.getCarCatByLId(newLId);
        if(null == carCategoryDO){
            return Result.wrapSuccessfulResult(null);
        }
        CarCategoryDTO result = BdUtil.do2bo(carCategoryDO, CarCategoryDTO.class);

        return Result.wrapSuccessfulResult(result);
    }

    @Override
    public Result<List<CarListDTO>> categoryCarInfo(Integer pId) {
        if (pId == null || pId < 0) {
            return Result.wrapErrorResult(null, "参数不能小于0或等于null!");
        }
        List<CarListBO> list = carCategoryManager.getCarForOldDataByPid(pId);

        List<CarListDTO> resultList = BdUtil.do2bo4List(list, CarListDTO.class);

        return Result.wrapSuccessfulResult(resultList);
    }

    @Override
    public Result<List<CarListSuit4GoodsDTO>> getCartListByGoodsId(Integer goodsId) {
        if (goodsId == null || goodsId < 0) {
            return Result.wrapErrorResult(null, "参数不能小于0或等于null!");
        }
        List<CarListSuit4GoodsBO> list = carCategoryManager.getCartListByGoodsId(goodsId);
        if(null == list){
            return Result.wrapErrorResult(null, "没有找到商品！");
        }
        //内部静态类 通过处理转换
        List<CarListSuit4GoodsDTO> resultList = new ArrayList<>();
        for(CarListSuit4GoodsBO carListSuit4GoodsBO : list){
            List<CarListSuit4GoodsBO.Model> modelList = carListSuit4GoodsBO.getModels();

            CarListSuit4GoodsDTO carListSuit4GoodsDTO = BdUtil.do2bo(carListSuit4GoodsBO, CarListSuit4GoodsDTO.class);
            List<CarListSuit4GoodsDTO.Model> resultModelList = new ArrayList<>();
            for(CarListSuit4GoodsBO.Model model : modelList){
                List<CarListSuit4GoodsBO.Displacement> displacementsList = model.getDisplacements();

                CarListSuit4GoodsDTO.Model resultModel = BdUtil.do2bo(model, CarListSuit4GoodsDTO.Model.class);
                List<CarListSuit4GoodsDTO.Displacement> resultDisplaceList = BdUtil.do2bo4List(displacementsList, CarListSuit4GoodsDTO.Displacement.class);
                resultModel.setDisplacements(resultDisplaceList);
                resultModelList.add(resultModel);
            }
            carListSuit4GoodsDTO.setModels(resultModelList);
            resultList.add(carListSuit4GoodsDTO);
        }

        return Result.wrapSuccessfulResult(resultList);
    }

    @Override
    public Result<List<HotCarBrandDTO>> getHotCarBrandList(Integer cityId) {

        Result<List<com.tqmall.tqmallstall.domain.result.carcategory.HotCarBrandDTO>> result = null;
        try {
            result = carConsumeService.getHotCarBrandList(cityId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
//            e.printStackTrace();
            return Result.wrapErrorResult(null,"hotCarFromStall wrong");
        }

        boolean success = result.isSuccess();
        if(!success){
            return Result.wrapErrorResult(null, result.getMessage());
        }

        //有数据

        List<com.tqmall.tqmallstall.domain.result.carcategory.HotCarBrandDTO> fromList = result.getData();
        List<HotCarBrandDTO> finalList = BdUtil.do2bo4List(fromList, HotCarBrandDTO.class);
        fromList = null;
        return Result.wrapSuccessfulResult(finalList);
    }

    @Override
    public Result<CarCategoryDTO> getCarCategoryByPrimaryId(Integer carId) {
        if (carId == null) {
            return Result.wrapErrorResult("0", "传入的车型id为空！");
        }
        CarCategoryDO carCategoryDO = carCategoryManager.getCarCategoryByPrimaryId(carId);
        if(null == carCategoryDO){
            return Result.wrapErrorResult("0", "不存在此id对应的车型！");
        }

        CarCategoryDTO carCategoryDTO = BdUtil.do2bo(carCategoryDO, CarCategoryDTO.class);
        carCategoryDO = null;

        return Result.wrapSuccessfulResult(carCategoryDTO);
    }

    @Override
    public Result<List<CarCategoryDTO>> getByLevel(Integer level) {
        Result<List<CarCategoryDO>> result = carCategoryManager.findByLevel(level);
        if(result.isSuccess()){
            return Result.wrapSuccessfulResult(BdUtil.do2bo4List(result.getData(), CarCategoryDTO.class));
        }else{
            return Result.wrapErrorResult(result.getCode(), result.getMessage());
        }
    }

    @Override
    public Result<List<CarCategoryDTO>> getByPidList(List<Integer> pidList) {
        Result<List<CarCategoryDO>> result = carCategoryManager.findByPidList(pidList);
        if(result.isSuccess()){
            return Result.wrapSuccessfulResult(BdUtil.do2bo4List(result.getData(), CarCategoryDTO.class));
        }else{
            return Result.wrapErrorResult(result.getCode(), result.getMessage());
        }
    }

    @Override
    public Result<List<CarCategoryDTO>> getByPrimaryIdList(List<Integer> ids) {
        Result<List<CarCategoryDO>> result = carCategoryManager.findByPrimaryIdList(ids);
        if(result.isSuccess()){
            return Result.wrapSuccessfulResult(BdUtil.do2bo4List(result.getData(), CarCategoryDTO.class));
        }else{
            return Result.wrapErrorResult(result.getCode(), result.getMessage());
        }
    }

    @Override
    public Result<List<CarCategoryDTO>> getByCarModels(List<Integer> otherModels, List<String> brandNames) {
        Result<List<CarCategoryDO>> result = carCategoryManager.findByCarModels(otherModels, brandNames);
        if(result.isSuccess()){
            return Result.wrapSuccessfulResult(BdUtil.do2bo4List(result.getData(), CarCategoryDTO.class));
        }else{
            return Result.wrapErrorResult(result.getCode(), result.getMessage());
        }
    }

    @Override
    public Result<List<String>> getFirstWords() {
        return carCategoryManager.findFirstWords();
    }

    @Override
    public Result<List<CarCategoryDTO>> getByFirstWord(String firstWord) {
        Result<List<CarCategoryDO>> result = carCategoryManager.findByFirstWord(firstWord);
        if(result.isSuccess()){
            return Result.wrapSuccessfulResult(BdUtil.do2bo4List(result.getData(), CarCategoryDTO.class));
        }else{
            return Result.wrapErrorResult(result.getCode(), result.getMessage());
        }
    }

    @Override
    public Result<List<CarCategoryDTO>> getByLevelList(List<Integer> levels) {
        Result<List<CarCategoryDO>> result = carCategoryManager.findByLevelList(levels);
        if(result.isSuccess()){
            return Result.wrapSuccessfulResult(BdUtil.do2bo4List(result.getData(), CarCategoryDTO.class));
        }else{
            return Result.wrapErrorResult(result.getCode(), result.getMessage());
        }
    }

    @Override
    public Result<List<CarCategoryDTO>> getByFirstWordLevel(String firstWord, Integer level) {
        Result<List<CarCategoryDO>> result = carCategoryManager.findByFirstWordLevel(firstWord, level);
        if(result.isSuccess()){
            return Result.wrapSuccessfulResult(BdUtil.do2bo4List(result.getData(), CarCategoryDTO.class));
        }else{
            return Result.wrapErrorResult(result.getCode(), result.getMessage());
        }
    }
}
