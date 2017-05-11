package com.tqmall.athena.external.car;

import com.tqmall.core.common.entity.Result;
import com.tqmall.tqmallstall.domain.result.carcategory.HotCarBrandDTO;
import com.tqmall.tqmallstall.service.carcategory.CarCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zxg on 15/10/12.
 * 调用stall的car 方法
 */

@Service
public class CarConsumeService {

    @Autowired
    private CarCategoryService carStallCategoryService;

    //均为stall的内容
    public Result<List<HotCarBrandDTO>> getHotCarBrandList(Integer cityId ){
        return carStallCategoryService.getHotCarBrandList(cityId);
    }
}
