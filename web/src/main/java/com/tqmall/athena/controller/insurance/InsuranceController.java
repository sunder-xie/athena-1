package com.tqmall.athena.controller.insurance;

import com.tqmall.athena.bean.entity.insurance.InsuranceCompanyDO;
import com.tqmall.athena.bussiness.insurance.InsuranceCompanyManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangzhangting on 15/11/27.
 */

@RestController
@RequestMapping("athena/insurance")
@Slf4j
public class InsuranceController {

    @Autowired
    private InsuranceCompanyManager insuranceCompanyManager;

    //所有保险公司数据
    //@RequestMapping(value = "company", method = RequestMethod.GET)
    //@ResponseBody
    public Result insuranceCompany(){
        try {
            List<Map<String,Object>> resultList = new ArrayList<>();
            List<InsuranceCompanyDO> list= insuranceCompanyManager.findInsuranceCompany();
            for(InsuranceCompanyDO company: list){
                Map<String,Object> map = new HashMap<>();
                map.put("id", company.getId());
                map.put("companyName", company.getCompanyName());
                map.put("phone", company.getPhone());
                map.put("sort", company.getSort());
                resultList.add(map);
            }
            return Result.wrapSuccessfulResult(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
            return Result.wrapErrorResult("001", e.getMessage());
        }
    }
}
