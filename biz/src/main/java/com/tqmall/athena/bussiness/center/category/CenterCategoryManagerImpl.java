package com.tqmall.athena.bussiness.center.category;

import com.tqmall.athena.bean.common.ShareConstants;
import com.tqmall.athena.bean.entity.center.category.CenterCategoryShowDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.redisBiz.center.category.CenterCategoryRedisManager;
import com.tqmall.core.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zxg on 16/10/26.
 * 19:27
 * no bug,以后改代码的哥们，祝你好运~！！
 */
@Service
public class CenterCategoryManagerImpl implements CenterCategoryManager {

    @Autowired
    private CenterCategoryRedisManager centerCategoryRedisManager;

    @Override
    public Result<List<CenterCategoryShowDO>> getCategoryShowBySourcePid(Integer source, Integer pid,String vehicleType) {
        if(!CenterCategoryShowDO.isInShowSource(source)){
            return ResultUtil.errorResult("000","source is wrong");
        }
        if(vehicleType != null && !vehicleType.equals(ShareConstants.PASSENGER_CAR) && !vehicleType.equals(ShareConstants.COMMERCIAL_CAR)){
            return ResultUtil.errorResult("001","vehicleType is wrong");
        }
        if(pid == null || pid < 0){
            pid = 0;
        }
        List<CenterCategoryShowDO> list = centerCategoryRedisManager.getCategoryShowBySourcePid(source, pid);
        // 移除非此类型的分类展示
        if(vehicleType != null){
            Iterator<CenterCategoryShowDO> showDOIterator = list.iterator();
            while (showDOIterator.hasNext()){
                CenterCategoryShowDO showDO = showDOIterator.next();
                if(!showDO.getVehicleCode().equals(vehicleType)){
                    showDOIterator.remove();
                }
            }
        }

        if(list.isEmpty()){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return ResultUtil.successResult(list);
    }
}
