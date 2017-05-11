package com.tqmall.athena.test.dubboTest.maintain;

import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.athena.bean.entity.maintain.MaintainDetail;
import com.tqmall.athena.bean.entity.maintain.MaintainItemDO;
import com.tqmall.athena.bussiness.maintain.MaintainManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huangzhangting on 15/9/20.
 */
public class MaintainTest extends BaseDubboTest {
    @Autowired
    private MaintainManager maintainManager;

    @Test
    public void test() throws Exception{
        List<MaintainItemDO> items = maintainManager.findItems();
        System.out.println("========= 保养项数量："+items.size());

        Integer carId = 48869;
        Integer mile = 29000;
//        List<ModelMaintainRelationDO> relations = maintainManager.findRelations(carId, mile);
//        System.out.println(relations.size());
//
//        List<Integer> miles = maintainManager.findMiles(carId);
//        System.out.println(miles.size());
//
//        Integer closeMile = maintainManager.findCloseMile(carId, mile);
//        System.out.println(closeMile);

        List<MaintainDetail> details = maintainManager.findMaintainDetail(carId, null);
        for(MaintainDetail detail : details){
            System.out.println(detail.toString());
        }
    }

}
