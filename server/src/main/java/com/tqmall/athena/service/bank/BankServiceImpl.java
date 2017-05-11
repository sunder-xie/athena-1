package com.tqmall.athena.service.bank;

import com.tqmall.athena.bean.bizBean.bank.BankTqAreaBO;
import com.tqmall.athena.bean.entity.bank.BankBmpDO;
import com.tqmall.athena.bean.entity.bank.BankKeywordDO;
import com.tqmall.athena.bussiness.bank.BankManager;
import com.tqmall.athena.client.bank.BankService;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.param.bank.BankBmpPO;
import com.tqmall.athena.domain.result.bank.BankBmpDTO;
import com.tqmall.athena.domain.result.bank.BankKeywordDTO;
import com.tqmall.athena.domain.result.bank.BmpTqAreaDTO;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by lyj on 16/4/25.
 */
@Slf4j
public class BankServiceImpl implements BankService {

    @Autowired
    private BankManager bankManager;

    @Override
    public Result<List<BankKeywordDTO>> getAllBankName() {
        Result<List<BankKeywordDO>> result = bankManager.getAllBankName();
        return ResultUtil.handleResult4List(result, BankKeywordDTO.class);
    }

    @Override
    public Result<List<BmpTqAreaDTO>> getBmpCity(String keyword, Integer provinceId) {
        Result<List<BankTqAreaBO>> result = bankManager.getBmpCity(keyword, provinceId);
        return ResultUtil.handleResult4List(result, BmpTqAreaDTO.class);
    }

    @Override
    public Result<List<BmpTqAreaDTO>> getBmpDistrict(String keyword, Integer provinceId, Integer cityId) {
        Result<List<BankTqAreaBO>> result = bankManager.getBmpDistrict(keyword, provinceId, cityId);
        return ResultUtil.handleResult4List(result, BmpTqAreaDTO.class);
    }

    @Override
    public Result<List<BankBmpDTO>> getBankBmp(BankBmpPO bmp) {
        if (bmp == null) {
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        Result<List<BankBmpDO>> result = bankManager.getBankBmp(bmp.getKeyword(), bmp.getProvinceId(), bmp.getCityId(),
                bmp.getDistrictId());
        return ResultUtil.handleResult4List(result, BankBmpDTO.class);
    }

}
