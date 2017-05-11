package com.tqmall.athena.redisBiz.bank;

import com.tqmall.athena.bean.bizBean.bank.BankTqAreaBO;
import com.tqmall.athena.bean.entity.bank.BankBmpDO;
import com.tqmall.athena.bean.entity.bank.BankKeywordDO;

import java.util.List;

/**
 * Created by lyj on 16/4/25.
 */
public interface BankRedisManager {

    List<BankKeywordDO> getAllBankName();

    List<BankBmpDO> getBankBmp(String keyword, Integer provinceId, Integer cityId, Integer districtId);

    List<BankTqAreaBO> getBmpCity(String keyword, Integer provinceId);

    List<BankTqAreaBO> getBmpDistrict(String keyword, Integer provinceId, Integer cityId);
}
