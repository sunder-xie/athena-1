package com.tqmall.athena.bussiness.bank;

import com.tqmall.athena.bean.bizBean.bank.BankTqAreaBO;
import com.tqmall.athena.bean.entity.bank.BankBmpDO;
import com.tqmall.athena.bean.entity.bank.BankKeywordDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.redisBiz.bank.BankRedisManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by lyj on 16/4/25.
 */
@Service
@Slf4j
public class BankManagerImpl implements BankManager {
    @Autowired
    private BankRedisManager bankRedisManager;


    @Override
    public Result<List<BankKeywordDO>> getAllBankName() {
        List<BankKeywordDO> list = bankRedisManager.getAllBankName();
        if (list.isEmpty()) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        Collections.sort(list, new Comparator<BankKeywordDO>() {
            @Override
            public int compare(BankKeywordDO o1, BankKeywordDO o2) {
                return o1.getSortNum().compareTo(o2.getSortNum());
            }
        });
        return ResultUtil.successResult(list);
    }

    @Override
    public Result<List<BankBmpDO>> getBankBmp(String keyword, Integer provinceId, Integer cityId, Integer districtId) {
        if (StringUtils.isEmpty(keyword)) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<BankBmpDO> list;
        if (Integer.valueOf(0).equals(districtId)) {
            // 业务需求, 若districtId字段值为0, 实际上按照城市匹配, 即最后一个形参置为null
            list = bankRedisManager.getBankBmp(keyword, provinceId, cityId, null);
        } else {
            list = bankRedisManager.getBankBmp(keyword, provinceId, cityId, districtId);

            // 业务需求, 若通过区县查询没有数据, 则去掉区县再查询一次
            if (list.isEmpty()) {
                list = bankRedisManager.getBankBmp(keyword, provinceId, cityId, null);
            }
        }

        if (list.isEmpty()) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        return ResultUtil.successResult(list);
    }

    @Override
    public Result<List<BankTqAreaBO>> getBmpCity(String keyword, Integer provinceId) {
        if (StringUtils.isEmpty(keyword)) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<BankTqAreaBO> list = bankRedisManager.getBmpCity(keyword, provinceId);
        if (list.isEmpty()) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return ResultUtil.successResult(list);
    }

    @Override
    public Result<List<BankTqAreaBO>> getBmpDistrict(String keyword, Integer provinceId, Integer cityId) {
        if (StringUtils.isEmpty(keyword) || provinceId == null || cityId == null) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        List<BankTqAreaBO> list = bankRedisManager.getBmpDistrict(keyword, provinceId, cityId);
        if (list.isEmpty()) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        return ResultUtil.successResult(list);
    }

}
