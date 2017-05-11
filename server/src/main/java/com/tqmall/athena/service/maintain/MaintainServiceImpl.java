package com.tqmall.athena.service.maintain;

import com.tqmall.athena.bean.common.ShareConstants;
import com.tqmall.athena.bean.entity.maintain.AdviseMaintainBO;
import com.tqmall.athena.bean.entity.maintain.MaintainDetail;
import com.tqmall.athena.bean.entity.maintain.MaintainItemDO;
import com.tqmall.athena.bussiness.maintain.MaintainManager;
import com.tqmall.athena.client.maintain.MaintainService;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.utils.BdUtil;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.domain.param.maintain.CarMaintainPO;
import com.tqmall.athena.domain.result.maintain.AdviseMaintainDTO;
import com.tqmall.athena.domain.result.maintain.ItemDTO;
import com.tqmall.athena.domain.result.maintain.MaintainDetailDTO;
import com.tqmall.athena.domain.result.maintain.MaintainItemDTO;
import com.tqmall.core.common.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhangting on 15/9/21.
 */

public class MaintainServiceImpl implements MaintainService {
    private static Logger log = LoggerFactory.getLogger(MaintainServiceImpl.class);

    @Autowired
    private MaintainManager maintainManager;

    @Override
    public Result<List<Integer>> getMiles(Integer carId) {
        if(carId==null || carId<0){
            log.info("getMiles error illegal carId "+carId);
            return Result.wrapErrorResult("", "输入参数错误，carId："+carId);
        }

        List<Integer> miles = maintainManager.findMiles(carId);
        return Result.wrapSuccessfulResult(miles);
    }

    @Override
    public Result<List<ItemDTO>> getItems() {
        List<ItemDTO> result = new ArrayList<>();
        List<MaintainItemDO> itemList = maintainManager.findItems();
        for(MaintainItemDO itemDO : itemList){
            if(itemDO.getIsCommon()){ //过滤掉通用保养项目（因为是老接口，得适应原来的逻辑）
                continue;
            }
            result.add(BdUtil.do2bo(itemDO, ItemDTO.class));
        }
        return Result.wrapSuccessfulResult(result);
    }

    @Override
    public Result<List<MaintainDetailDTO>> getMaintainDetail(Integer carId, Integer mile) {
        if(carId==null || carId<0){
            log.info("getMaintainDetail error illegal carId "+carId);
            return Result.wrapErrorResult("", "输入参数错误，carId："+carId);
        }
        if(mile!=null && mile<0){
            log.info("getMaintainDetail error illegal mile "+mile);
            return Result.wrapErrorResult("", "输入参数错误，mile："+mile);
        }

        List<MaintainDetail> detailList = maintainManager.findMaintainDetail(carId, mile);
        List<MaintainDetailDTO> result = BdUtil.do2bo4List(detailList, MaintainDetailDTO.class);
        return Result.wrapSuccessfulResult(result);
    }

    @Override
    public Result<AdviseMaintainDTO> getAdviseMaintain(CarMaintainPO param) {
        if(param==null) {
            return Result.wrapErrorResult("", "请输入正确参数");
        }

        if(param.getYearId()==null || param.getYearId()<=0 || param.getMileage()==null || param.getMileage()<0){
            return Result.wrapErrorResult("", "请输入正确参数");
        }

        if(param.getMileage() > ShareConstants.MAX_MAINTAIN_MILE){
            return Result.wrapErrorResult("", "输入里程超出上线:"+ShareConstants.MAX_MAINTAIN_MILE+", 输入里程:"+param.getMileage());
        }

        AdviseMaintainBO maintainBO = maintainManager.adviseMaintainByYearId(param.getYearId(), param.getMileage());
        if(maintainBO==null){
            return Result.wrapErrorResult("", "数据缺失补充中");
        }

        AdviseMaintainDTO maintainDTO = new AdviseMaintainDTO();
        maintainDTO.setYearId(maintainBO.getYearId());
        maintainDTO.setMileage(maintainBO.getMileage());
        maintainDTO.setItems(maintainBO.getItems());

        return Result.wrapSuccessfulResult(maintainDTO);
    }

    @Override
    public Result<List<MaintainDetailDTO>> maintainDetailCommon(Integer carId, Integer mile) {
        if(mile!=null){
            if(mile<0){
                return Result.wrapErrorResult("", "输入里程不能为负数");
            }
            if(mile > ShareConstants.MAX_MAINTAIN_MILE){
                return Result.wrapErrorResult("", "输入里程超出上线:"+ShareConstants.MAX_MAINTAIN_MILE+", 输入里程:"+mile);
            }
        }

        Result<List<MaintainDetail>> manageResult = maintainManager.findMaintainDetailCommon(carId, mile);
        if(manageResult.isSuccess()){
            return Result.wrapSuccessfulResult(BdUtil.do2bo4List(manageResult.getData(), MaintainDetailDTO.class));
        }else{
            return Result.wrapErrorResult(manageResult.getCode(), manageResult.getMessage());
        }
    }

    @Override
    public Result<List<MaintainItemDTO>> allMaintainItems() {
        List<MaintainItemDO> list = maintainManager.findItems();
        return ResultUtil.successResult(BdUtil.do2bo4List(list, MaintainItemDTO.class));
    }

    @Override
    public Result<Integer> nextMaintainMileage(Integer carId, Integer mileage) {
        if(carId==null || carId<1 || mileage==null || mileage<0){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }

        Result<List<MaintainDetailDTO>> result = maintainDetailCommon(carId, mileage);
        if(result.isSuccess()){
            return ResultUtil.successResult(result.getData().get(0).getMile());
        }
        return ResultUtil.errorResult(result.getCode(), result.getMessage());
    }
}
