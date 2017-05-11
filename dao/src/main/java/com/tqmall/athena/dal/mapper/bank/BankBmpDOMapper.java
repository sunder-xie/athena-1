package com.tqmall.athena.dal.mapper.bank;

import com.tqmall.athena.bean.bizBean.bank.BankTqAreaBO;
import com.tqmall.athena.bean.entity.bank.BankBmpDO;
import com.tqmall.athena.dal.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface BankBmpDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankBmpDO record);

    int insertSelective(BankBmpDO record);

    BankBmpDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankBmpDO record);

    int updateByPrimaryKey(BankBmpDO record);

    List<BankBmpDO> selectBankBmp(@Param("keyword") String keyword, @Param("provinceId") Integer provinceId, @Param("cityId") Integer cityId,
                                  @Param("districtId") Integer districtId);

    List<BankTqAreaBO> selectBmpCity(@Param("keyword") String keyword, @Param("provinceId") Integer provinceId);

    List<BankTqAreaBO> selectBmpDistrict(@Param("keyword") String keyword,@Param("provinceId")  Integer provinceId, @Param("cityId") Integer cityId);
}