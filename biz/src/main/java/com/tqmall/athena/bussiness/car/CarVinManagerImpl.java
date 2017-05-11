package com.tqmall.athena.bussiness.car;

import com.tqmall.athena.bean.bizBean.car.CarBO;
import com.tqmall.athena.bean.common.ShareConstants;
import com.tqmall.athena.bean.entity.car.CarAllDO;
import com.tqmall.athena.common.bean.DataError;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.DateUtil;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.common.utils.ResultUtil;
import com.tqmall.athena.common.utils.StrUtil;
import com.tqmall.athena.dal.mapper.car.CarVinDOMapper;
import com.tqmall.athena.external.http.CarVinExt;
import com.tqmall.athena.external.vin.ExtVinService;
import com.tqmall.athena.redisBiz.car.CarRedisManager;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by huangzhangting on 16/6/15.
 */
@Slf4j
@Service
public class CarVinManagerImpl implements CarVinManager {
    //限制一天300条
    private static final Integer LIMIT_GET_VIN_MAX_NUM = 300;

    @Value("${project.environment}")
    private String environment;

    @Autowired
    private RedisClientTemplate redisClient;
    @Autowired
    private CarVinDOMapper carVinDOMapper;
    @Autowired
    private CarRedisManager carRedisManager;
    @Autowired
    private CarVinExt carVinExt;
    @Autowired
    private ExtVinService extVinService;


    @Override
    public Result<List<CarBO>> getCarBOByVin(String vin) {
        //无限制
        return getCarBOByVinForLimit(vin,false);
    }

    @Override
    public Result<List<CarBO>> getCarBOByVinLimit(String vin) {
        return getCarBOByVinForLimit(vin,true);
    }

    //获得liyang数据
    private Result<List<CarBO>> getCarBOByVinForLimit(String vin,Boolean isLimit){
        if(!StrUtil.isVin(vin)){
            return ResultUtil.errorResult(DataError.ARG_ERROR);
        }
        vin = vin.replace(" ", "").toUpperCase();

        //从redis获取数据
        String key = String.format(RedisKeyBean.CAR_LIST_BY_VIN, vin);
        String redisStr = redisClient.get(key);
        if(redisClient.isNone(redisStr)){
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }

        List<CarBO> list = null;
        if(redisStr!=null){
            list = JsonUtil.jsonStrToList(redisStr, CarBO.class);
            if(list.isEmpty()){
                return ResultUtil.errorResult(DataError.UNKNOW_EXCEPTION);
            }
            return ResultUtil.successResult(list);
        }

        //从数据库查询力洋id
        List<String> lyIdList = carVinDOMapper.selectLIdByVin(vin);
        if(lyIdList.isEmpty()){
            //如果查不到，并且是生产环境，就调用力洋接口
            if(ShareConstants.ENVIRONMENT_ONLINE.equals(environment)){
                //数据补充,
                Boolean allowContinueGet = true;
                //是否有
                if(isLimit){
                    allowContinueGet  = isAllowedGetVinServer();
                }
                if(allowContinueGet) {
//                    lyIdList = carVinExt.getLyIdsFromLiYang(vin);
                    lyIdList = extVinService.getLyIdsFromLiYang(vin);
                }
            }
        }

        if(!lyIdList.isEmpty()){
            list = getCarList(lyIdList);
        }

        if(CollectionUtils.isEmpty(list)){
            redisClient.setNone(key, RedisKeyBean.RREDIS_EXP_FIVE_MINUTE);
            return ResultUtil.errorResult(DataError.NO_DATA_ERROR);
        }

        redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_HOURS);
        return ResultUtil.successResult(list);
    }

    //判断 调用vinserver是否超过限制
    private Boolean isAllowedGetVinServer(){
        String today = DateUtil.dateToString(new Date(),DateUtil.yyyy_MM_dd);
        String key = String.format(RedisKeyBean.VIN_LIMIT_TODAY,today);


        Integer todayUsedNum = redisClient.lazyGet(key,Integer.class);
        if(todayUsedNum == null) todayUsedNum = 0;
        if(todayUsedNum >= LIMIT_GET_VIN_MAX_NUM){
            return false;
        }

        //增加一次调用
        todayUsedNum ++;
        redisClient.lazySet(key,todayUsedNum,RedisKeyBean.RREDIS_EXP_DAY);

        return true;
    }

    //根据力洋id，查询车型数据，并去重
    private List<CarBO> getCarList(List<String> lyIdList){
        List<CarBO> list = new ArrayList<>();
        Set<Integer> carIdSet = new HashSet<>();
        for(String lyId : lyIdList){
            CarAllDO carAllDO = carRedisManager.getCarAllByLiyangId(lyId);
            if(carAllDO!=null && carIdSet.add(carAllDO.getCarModelsId())){
                CarBO carBO = new CarBO();

                carBO.setYear(carAllDO.getYear());
                carBO.setYearId(carAllDO.getYearId());
                carBO.setPower(carAllDO.getPower());
                carBO.setPowerId(carAllDO.getPowerId());
                carBO.setModel(carAllDO.getModel());
                carBO.setModelId(carAllDO.getModelId());
                carBO.setSeries(carAllDO.getSeries());
                carBO.setSeriesId(carAllDO.getSeriesId());
                carBO.setBrand(carAllDO.getBrand());
                carBO.setBrandId(carAllDO.getBrandId());

                carBO.setId(carAllDO.getCarModelsId());
                carBO.setName(carAllDO.getCarModels());

                StringBuilder sb = new StringBuilder(40);
                sb.append(carAllDO.getBrand()).append(" ");
                sb.append(carAllDO.getSeries()).append(" ");
                sb.append(carAllDO.getModel()).append(" ");
                sb.append(carAllDO.getYear()).append(" ");
                sb.append(carAllDO.getCarModels());
                carBO.setCarName(sb.toString());

                list.add(carBO);
            }
        }
        return list;
    }

}
