package com.tqmall.athena.bussiness.bank;

import com.tqmall.athena.bean.bizBean.bank.BankTqAreaBO;
import com.tqmall.athena.bean.entity.bank.BankBmpDO;
import com.tqmall.athena.bean.entity.bank.BankKeywordDO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * Created by lyj on 16/4/25.
 */
public interface BankManager {

    Result<List<BankKeywordDO>> getAllBankName();

    Result<List<BankBmpDO>> getBankBmp(String keyword, Integer provinceId, Integer cityId, Integer districtId);

    Result<List<BankTqAreaBO>> getBmpCity(String keyword, Integer provinceId);

    Result<List<BankTqAreaBO>> getBmpDistrict(String keyword, Integer provinceId, Integer cityId);
}
