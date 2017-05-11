package com.tqmall.athena.test.dubboTest.maintain;

import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.domain.param.maintain.CarMaintainPO;
import com.tqmall.athena.domain.result.maintain.AdviseMaintainDTO;
import com.tqmall.athena.domain.result.maintain.MaintainItemDTO;
import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.athena.client.maintain.MaintainService;
import com.tqmall.athena.domain.result.maintain.ItemDTO;
import com.tqmall.athena.domain.result.maintain.MaintainDetailDTO;
import com.tqmall.core.common.entity.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by huangzhangting on 15/9/21.
 */
public class MaintainDubboTest extends BaseDubboTest {
    @Autowired
    private MaintainService maintainService;

    @Test
    public void maintainTest() throws Exception {
        Integer carId = 49443;
        Result<List<Integer>> mileResult = maintainService.getMiles(carId);
        assertTrue(mileResult.isSuccess());

        Result<List<ItemDTO>> itemResult = maintainService.getItems();
        assertTrue(itemResult.isSuccess());

        Result<List<MaintainDetailDTO>> detailResult = maintainService.getMaintainDetail(carId, 30000);
        assertTrue(detailResult.isSuccess());
        System.out.println(detailResult.getData());

        List<CarMaintainPO> poList = new ArrayList<>();
        CarMaintainPO po = new CarMaintainPO();
        po.setYearId(84454);
        po.setMileage(320000);
        poList.add(po);

        po = new CarMaintainPO();
        po.setYearId(53860);
        po.setMileage(50000);
        poList.add(po);

//        Result<Map<Integer, AdviseMaintainDTO>> adviseMaintain = maintainService.getAdviseMaintain(poList);
//        for(Map.Entry entry : adviseMaintain.getData().entrySet()){
//            System.out.println(entry.getKey()+" : "+entry.getValue());
//        }

        Result<AdviseMaintainDTO> adMain = maintainService.getAdviseMaintain(po);
        System.out.println(JsonUtil.objectToJsonStr(adMain));


        carId = 90662;
        detailResult = maintainService.maintainDetailCommon(carId, 0);
        System.out.println(JsonUtil.objectToJsonStr(detailResult));

    }

    @Test
    public void test() throws Exception{
        Result<List<ItemDTO>> result = maintainService.getItems();
        System.out.println(JsonUtil.objectToJsonStr(result));

        Result<List<MaintainItemDTO>> result1 = maintainService.allMaintainItems();
        System.out.println(JsonUtil.objectToJsonStr(result1));
    }

}
