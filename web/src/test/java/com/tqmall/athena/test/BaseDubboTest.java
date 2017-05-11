package com.tqmall.athena.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by zxg on 15/9/20.
 */
@ContextConfiguration(locations = "classpath*:conf/application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true) //事务回滚，数据不会对数据库造成影响
public abstract class BaseDubboTest {
}
