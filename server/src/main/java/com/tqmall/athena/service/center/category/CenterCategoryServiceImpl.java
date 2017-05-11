package com.tqmall.athena.service.center.category;

import com.tqmall.athena.bean.entity.center.category.CenterCategoryShowDO;
import com.tqmall.athena.bussiness.center.category.CenterCategoryManager;
import com.tqmall.athena.client.center.category.CenterCategoryService;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.result.center.category.CenterCategoryShowDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zxg on 16/10/26.
 * 19:24
 * no bug,以后改代码的哥们，祝你好运~！！
 */
@Slf4j
public class CenterCategoryServiceImpl implements CenterCategoryService {

    @Autowired
    private CenterCategoryManager centerCategoryManager;

    @Override
    public Result<List<CenterCategoryShowDTO>> getCategoryShowBySourcePid(Integer showSource, Integer pid,String vehicleType) {
        Result<List<CenterCategoryShowDO>> result = centerCategoryManager.getCategoryShowBySourcePid(showSource, pid,vehicleType);
        return ResultUtil.handleResult4List(result, CenterCategoryShowDTO.class);
    }
}
