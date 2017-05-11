package com.tqmall.athena.test.dubboTest.bank;

import com.tqmall.athena.client.bank.BankService;
import com.tqmall.athena.domain.param.bank.BankBmpPO;
import com.tqmall.athena.domain.result.bank.BankBmpDTO;
import com.tqmall.athena.domain.result.bank.BankKeywordDTO;
import com.tqmall.athena.domain.result.bank.BmpTqAreaDTO;
import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.core.common.entity.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * bank service test
 * Created by lyj on 16/4/25.
 */
public class BankDubboTest extends BaseDubboTest {

    @Autowired
    private BankService bankService;

    @Test
    public void getAllBankName() throws Exception {
        Result<List<BankKeywordDTO>> result = bankService.getAllBankName();
        System.out.println(result.isSuccess());
        System.out.println(result.getData().size());

        if (result.isSuccess()) {
            for (BankKeywordDTO keyword : result.getData()) {
                System.out.println(keyword.getKeyword());
            }
        }
    }

    @Test
    public void getBankBmp() throws Exception {
        BankBmpPO bankBmpPO = new BankBmpPO();
        bankBmpPO.setKeyword("中国农业银行");
        bankBmpPO.setProvinceId(31);
        bankBmpPO.setCityId(383);
        bankBmpPO.setDistrictId(0);

        Result<List<BankBmpDTO>> result = bankService.getBankBmp(bankBmpPO);
        System.out.println(result.isSuccess());

        if (result.isSuccess()) {
            System.out.println(result.getData().size());
            System.out.println(result.getData());
        }
    }

    @Test
    public void getBmpCity() throws Exception {
        String keyword = "中国农业银行";
        Integer tqProvinceId = 31;

        Result<List<BmpTqAreaDTO>> result = bankService.getBmpCity(keyword, tqProvinceId);
        System.out.println(result.isSuccess());

        if (result.isSuccess()) {
            System.out.println(result.getData().size());
            System.out.println(result.getData());
        }
    }

    @Test
    public void getBmpDistrict() throws Exception {
        String keyword = "中国农业银行";
        Integer tqProvinceId = 31;
        Integer tqCityId = 383;

        Result<List<BmpTqAreaDTO>> result = bankService.getBmpDistrict(keyword, tqProvinceId, tqCityId);
        System.out.println(result.isSuccess());

        if (result.isSuccess()) {
            System.out.println(result.getData().size());
            System.out.println(result.getData());
        }
    }

}
