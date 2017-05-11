package com.tqmall.athena.client.bank;

import com.tqmall.athena.domain.param.bank.BankBmpPO;
import com.tqmall.athena.domain.result.bank.BankBmpDTO;
import com.tqmall.athena.domain.result.bank.BankKeywordDTO;
import com.tqmall.athena.domain.result.bank.BmpTqAreaDTO;
import com.tqmall.core.common.entity.Result;

import java.util.List;

/**
 * 银行数据接口
 * Created by lyj on 16/4/25.
 */
public interface BankService {

    /**
     * 获取所有银行关键词，数据无重复，排序字段(sort_num)升序。
     *
     * @return
     */
    Result<List<BankKeywordDTO>> getAllBankName();

    /**
     * 根据银行关键词和淘汽省级id, 获取所在省份的所有城市名称（有营业网点的城市），数据已去重，无排序。
     *
     * @return
     */
    Result<List<BmpTqAreaDTO>> getBmpCity(String keyword, Integer provinceId);

    /**
     * 根据银行关键词、淘汽省级id及淘气城市id, 获取所在省份城市的区、县等名称（有营业网点的区、县等），数据已去重，无排序。
     *
     * @return
     */
    Result<List<BmpTqAreaDTO>> getBmpDistrict(String keyword, Integer provinceId, Integer cityId);

    /**
     * 根据参数查询银行营业网点信息，数据数据无处理，无排序。
     *
     * @param bmp
     * @return
     */
    Result<List<BankBmpDTO>> getBankBmp(BankBmpPO bmp);
}
