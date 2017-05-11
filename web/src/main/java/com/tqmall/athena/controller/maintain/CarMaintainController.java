package com.tqmall.athena.controller.maintain;

import com.tqmall.athena.bean.entity.maintain.AdviseMaintainBO;
import com.tqmall.athena.bean.webParam.carMaintain.CarMaintainPO;
import com.tqmall.athena.bussiness.maintain.MaintainManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangzhangting on 15/11/7.
 */
@RestController
@RequestMapping("athena/carMaintain")
@Slf4j
public class CarMaintainController {
    @Autowired
    private MaintainManager maintainManager;


    //@RequestMapping(value = "adviseMaintainForYearId", method = RequestMethod.POST)
    //@ResponseBody
    public Result adviseMaintainForYearId(@RequestBody List<CarMaintainPO> paramList) {

        if(paramList==null || paramList.isEmpty()){
            return Result.wrapErrorResult("", "请输入正确参数！");
        }

        Map<Integer, AdviseMaintainBO> resultMap = new HashMap<>();
        try {
            AdviseMaintainBO maintainBO;
            for(CarMaintainPO po : paramList){
                maintainBO = maintainManager.adviseMaintainByYearId(po.getYearId(), po.getMileage());
                if(maintainBO==null){
                    continue;
                }
                resultMap.put(po.getYearId(), maintainBO);
            }
        }catch (Exception e){
            log.error("adviseMaintainForYearId error ", e);
            return Result.wrapErrorResult("", "系统异常！"+e.getMessage());
        }

        return Result.wrapSuccessfulResult(resultMap);
    }

}
