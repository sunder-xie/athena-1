package com.tqmall.athena.service.goods;

import com.tqmall.athena.bean.bizBean.params.CategoryQueryParam;
import com.tqmall.athena.bean.common.ShareConstants;
import com.tqmall.athena.bean.entity.goods.CategoryBrandDO;
import com.tqmall.athena.bean.entity.goods.GoodsCategoryDO;
import com.tqmall.athena.bussiness.goods.CategoryManager;
import com.tqmall.athena.client.goods.GoodsCategoryService;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.BdUtil;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.param.CategoryQueryPO;
import com.tqmall.athena.domain.result.goods.CategoryDTO;
import com.tqmall.athena.domain.result.goods.GoodsCateBrandDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangzhangting on 15/10/22.
 */
@Slf4j
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    @Autowired
    private CategoryManager categoryManager;

    @Override
    public Result<List<GoodsCateBrandDTO>> getCateBrands(Integer cateId) {
        Result<List<CategoryBrandDO>> result = categoryManager.findCateBrand(cateId);
        return ResultUtil.handleResult4List(result, GoodsCateBrandDTO.class);
    }

    @Override
    public Result<List<CategoryDTO>> getByCondition(CategoryQueryPO queryPO) {
        Result<List<GoodsCategoryDO>> result =
                categoryManager.findByCondition(BdUtil.do2bo(queryPO, CategoryQueryParam.class));
        return ResultUtil.handleResult4List(result, CategoryDTO.class);
    }

    @Override
    public Result<CategoryDTO> getCategoryById(Integer id) {
        if(id==null || id<=0){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        CategoryQueryParam param = new CategoryQueryParam();
        param.setId(id);
        Result<List<GoodsCategoryDO>> result = categoryManager.findByCondition(param);
        if(result.isSuccess()){
            return ResultUtil.successResult(BdUtil.do2bo(result.getData().get(0), CategoryDTO.class));
        }
        return ResultUtil.errorResult(result);
    }

    @Override
    public Result<List<CategoryDTO>> getCommercialCateByPid(Integer pid) {
        if(pid==null || pid<0){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        CategoryQueryParam param = new CategoryQueryParam();
        param.setPid(pid);
        param.setIsShow(ShareConstants.IS_SHOW);
        param.setVehicleCode(ShareConstants.COMMERCIAL_CAR);

        Result<List<GoodsCategoryDO>> result = categoryManager.findByCondition(param);
        return ResultUtil.handleResult4List(result, CategoryDTO.class);
    }
}
