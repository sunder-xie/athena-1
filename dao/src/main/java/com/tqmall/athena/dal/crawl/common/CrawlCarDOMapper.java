package com.tqmall.athena.dal.crawl.common;

import com.tqmall.athena.bean.crawl.common.CrawlCarDO;
import com.tqmall.athena.dal.MyBatisRepository;

@MyBatisRepository
public interface CrawlCarDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlCarDO record);

    int insertSelective(CrawlCarDO record);

    CrawlCarDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlCarDO record);

    int updateByPrimaryKey(CrawlCarDO record);
}