package com.tqmall.athena.external.insurance;

import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.core.common.entity.Result;
import com.tqmall.insurance.service.insurance.RpcPackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhangting on 16/9/19.
 */
@Slf4j
@Component
public class PackageServiceExt {
    @Autowired
    private RpcPackageService rpcPackageService;

    /**
     * 获取参与服务包的机滤列表
     *
     * @return 返回机滤的goodsSn列表
     */
    public List<String> queryPackageFilters(){
        Result<List<String>> result = rpcPackageService.queryPackageFilters();
        if(result!=null && !CollectionUtils.isEmpty(result.getData())){
            return result.getData();
        }

        log.info("queryPackageFilters failed, result:{}", JsonUtil.objectToJsonStr(result));
        return new ArrayList<>();
    }
}
