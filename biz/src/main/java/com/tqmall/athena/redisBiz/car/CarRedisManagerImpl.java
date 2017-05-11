package com.tqmall.athena.redisBiz.car;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tqmall.athena.bean.bizBean.car.CarListBO;
import com.tqmall.athena.bean.common.ShareConstants;
import com.tqmall.athena.bean.entity.car.CarAllDO;
import com.tqmall.athena.bean.entity.car.CarCategoryDO;
import com.tqmall.athena.bean.entity.car.GoodsCarDO;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.BdUtil;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.car.CarAllDOMapper;
import com.tqmall.athena.dal.mapper.car.CarCategoryDOMapper;
import com.tqmall.athena.dal.mapper.car.GoodsCarDOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by zxg on 15/9/22.
 * 车型缓存
 */
@Service
@Slf4j
public class CarRedisManagerImpl implements CarRedisManager {

    //主键id
    private static final String CAR_CATEGORY_ID_KEY = RedisKeyBean.CAR_CATEGORY_ID_KEY;
    //pid
    private static final String CAR_LIST_BY_PID_KEY = RedisKeyBean.CAR_LIST_BY_PID_KEY;
    ///力洋
    private static final String CAR_ALL_BY_LIYANG_KEY = RedisKeyBean.CAR_ALL_BY_LIYANG_KEY;

    //key：商品id value：适配车型列表
    private static final String CAR_LIST_BY_GOODS_KEY = RedisKeyBean.CAR_LIST_BY_GOODS_KEY;

    @Autowired
    private RedisClientTemplate redisClientTemplate;

    @Autowired
    private CarCategoryDOMapper carCategoryDOMapper;

    @Autowired
    private CarAllDOMapper carAllDOMapper;

    @Autowired
    private GoodsCarDOMapper goodsCarDOMapper;


    /*========================================基本缓存=====================================================================*/
    //primaryId 得到车型数据
    public CarCategoryDO getCarByPrimaryId(Integer id) {
        if (null == id || id < 0) {
            return null;
        }
        String key = String.format(CAR_CATEGORY_ID_KEY, id);
        String jsonString = redisClientTemplate.get(key);

        if (redisClientTemplate.isNone(jsonString)) {
            return null;
        }


        CarCategoryDO carCategoryDO = new CarCategoryDO();
        if (null == jsonString) {
            carCategoryDO = carCategoryDOMapper.selectByPrimaryKey(id);
            if (null == carCategoryDO) {
                redisClientTemplate.setNone(key);
                return null;
            }
            redisClientTemplate.lazySet(key, carCategoryDO, RedisKeyBean.RREDIS_EXP_DAY);
        } else {
            carCategoryDO = JsonUtil.jsonStrToObject(jsonString, CarCategoryDO.class);
        }
        return carCategoryDO;
    }

    //根据pid 获得其子car数据
    public List<CarCategoryDO> getCarListByPid(Integer pid) {
        if (null == pid || pid < 0) {
            return null;
        }
        String key = String.format(CAR_LIST_BY_PID_KEY, pid);

        List<CarCategoryDO> list = redisClientTemplate.lazyGetList(key, CarCategoryDO.class);
        if (null == list) {
            list = carCategoryDOMapper.selectByPid(pid);
            if (null != list) {
                redisClientTemplate.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
            } else {
                redisClientTemplate.lazySet(key, new ArrayList<>(), RedisKeyBean.RREDIS_EXP_HOURS);
            }
        }

        return list;
    }

    //根据力洋ID获得所有车型数据
    public CarAllDO getCarAllByLiyangId(String liyangId) {
        if (null == liyangId || liyangId.isEmpty()) {
            return null;
        }
        String key = String.format(CAR_ALL_BY_LIYANG_KEY, liyangId);
        String jsonString = redisClientTemplate.get(key);
        if (redisClientTemplate.isNone(jsonString)) {
            //缓存中无对应的线上id
            return null;
        }

        CarAllDO carAllDO = new CarAllDO();
        if (null == jsonString) {
            carAllDO = carAllDOMapper.selectCarByLiyangId(liyangId);
            if (null == carAllDO || null == carAllDO.getCarModelsId()) {
                //无对应的线上id
                redisClientTemplate.setNone(key);
                return null;
            }
            redisClientTemplate.lazySet(key, carAllDO, RedisKeyBean.RREDIS_EXP_DAY);
        } else {
            carAllDO = JsonUtil.jsonStrToObject(jsonString, CarAllDO.class);
        }

        return carAllDO;
    }

    //根据goodsId获得所有对应车型数据list
    public List<GoodsCarDO> getGoodsCarListByGoodsId(Integer goodsId) {

        if (null == goodsId || goodsId < 0) {
            return null;
        }
        String key = String.format(CAR_LIST_BY_GOODS_KEY, goodsId);

        List<GoodsCarDO> list = redisClientTemplate.lazyGetList(key, GoodsCarDO.class);

        if (null == list || list.isEmpty()) {
            list = goodsCarDOMapper.selectListByGoodsId(goodsId);
            if (null != list) {
                redisClientTemplate.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
            } else {
                redisClientTemplate.lazySet(key, new ArrayList<>(), RedisKeyBean.RREDIS_EXP_HOURS);
            }
        }

        return list;
    }


    /*========================================二次加工的函数====================================================================*/

    //根据传入的pids 获得子list
    public List<CarCategoryDO> getCarListByPid(List<Integer> pids) {
        List<CarCategoryDO> resultList = new ArrayList<>();
        for (Integer pid : pids) {
            List<CarCategoryDO> oneList = this.getCarListByPid(pid);
            if (oneList != null) {
                resultList.addAll(oneList);
            }
        }
        return resultList;
    }

    @Override
    public List<CarListBO> getCarForOldDataByPid(Integer pId) {
        String key = String.format(RedisKeyBean.CAR_FOR_4_OLD_BY_PID, pId);
        String jsonString = redisClientTemplate.get(key);
        if (redisClientTemplate.isNone(jsonString)) {
            //缓存中无对应的线上id
            return Collections.emptyList();
        }
        if(jsonString != null)
            return JsonUtil.jsonStrToList(jsonString, CarListBO.class);

        List<CarCategoryDO> carList = getCarListByPid(pId);
        if (null == carList || carList.isEmpty()) {
            log.info("can't find car，pID is：" + pId);
            redisClientTemplate.setNone(key,RedisKeyBean.RREDIS_EXP_DAY);
            return Collections.emptyList();
        }

        /*处理level-4、5、6级的数据——carList存第6级车*/
        Integer carLevel = carList.get(0).getLevel();
        if(carLevel > 3){
            carList = changeCarLevel4ToEnd(carList);
        }
        /***
         *  fullName拼写
         *  车型排序：国产在前，进口车在尾，合资在中间
         */
        List<CarListBO> finalResultList = this.sortCarCompany(carList);

        redisClientTemplate.lazySet(key, finalResultList,RedisKeyBean.RREDIS_EXP_DAY);
        return finalResultList;
    }

    @Override
    public List<CarCategoryDO> getByLevel(Integer level){
        String key = String.format(RedisKeyBean.CAR_LIST_BY_LEVEL, level);
        String redisStr = redisClientTemplate.get(key);

        if (redisClientTemplate.isNone(redisStr)) {
            return new ArrayList<>();
        }

        if (redisStr != null) {
            return JsonUtil.jsonStrToList(redisStr, CarCategoryDO.class);
        }

        List<CarCategoryDO> list = carCategoryDOMapper.selectByLevel(level);
        if (list.isEmpty()) {
            redisClientTemplate.setNone(key);
        } else {
            redisClientTemplate.setStringWithTime(key, JsonUtil.objectToJsonStr(list), RedisKeyBean.RREDIS_EXP_DAY);
        }

        return list;
    }

    /*********************************20160309 lyj 新增接口 start*******************************************************/
    @Override
    public List<CarCategoryDO> getByPrimaryIdList(List<Integer> ids) {
        List<CarCategoryDO> resultList = new ArrayList<>();
        for (Integer id : ids) {
            CarCategoryDO carCategoryDO = this.getCarByPrimaryId(id);
            if (carCategoryDO != null) {
                resultList.add(carCategoryDO);
            }
        }
        return resultList;
    }

    @Override
    public List<CarCategoryDO> getByCarModels(List<Integer> otherModels, List<String> brandNames) {
        List<CarCategoryDO> resultList = new ArrayList<>();
        for (Integer id : otherModels) {
            CarCategoryDO carCategoryDO = this.getCarByPrimaryId(id);
            if (carCategoryDO != null && carCategoryDO.getIsDel() == 0) {
                if (CollectionUtils.isEmpty(brandNames)) {
                    resultList.add(carCategoryDO);
                } else {
                    if (!brandNames.contains(carCategoryDO.getBrand())) {
                        resultList.add(carCategoryDO);
                    }
                }
            }
        }
        return resultList;
    }

    @Override
    public List<String> getFirstWords(){
        String key = RedisKeyBean.CAR_FIRST_WORDS;
        String redisStr = redisClientTemplate.get(key);

        if (redisClientTemplate.isNone(redisStr)) {
            return new ArrayList<>();
        }

        if (redisStr != null) {
            return JsonUtil.jacksonToCollection(redisStr, List.class, String.class);
        }

        List<String> list = carCategoryDOMapper.selectFirstWords();
        //去重
        List<String> listDistinct = listDistinctUseContains(list);
        if (list.isEmpty()) {
            redisClientTemplate.setNone(key);
        } else {
            redisClientTemplate.setStringWithTime(
                    key, JsonUtil.objectToJsonStr(listDistinct), RedisKeyBean.RREDIS_EXP_DAY);
        }
        return listDistinct;
    }

    @Override
    public List<CarCategoryDO> getByFirstWord(String firstWord){
        String key = String.format(RedisKeyBean.CAR_LIST_BY_FIRST_WORD, firstWord);
        String redisStr = redisClientTemplate.get(key);

        if (redisClientTemplate.isNone(redisStr)) {
            return new ArrayList<>();
        }

        if (redisStr != null) {
            return JsonUtil.jacksonToCollection(redisStr, List.class, CarCategoryDO.class);
        }

        List<CarCategoryDO> list = carCategoryDOMapper.selectByFirstWord(firstWord);
        if (list.isEmpty()) {
            redisClientTemplate.setNone(key);
        } else {
            redisClientTemplate.setStringWithTime(
                    key, JsonUtil.objectToJsonStr(list), RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

    @Override
    public List<CarCategoryDO> getByLevelList(List<Integer> levels){
        List<CarCategoryDO> resultList = new ArrayList<>();
        List<Integer> treatedLevels= listDistinctUseContains(levels);
        for (Integer level : treatedLevels) {
            List<CarCategoryDO> oneList = this.getByLevel(level);
            if (!CollectionUtils.isEmpty(oneList)) {
                resultList.addAll(oneList);
            }
        }
        return resultList;
    }

    @Override
    public List<CarCategoryDO> getByFirstWordLevel(String firstWord, Integer level){
        List<CarCategoryDO> resultList = new ArrayList<>();
        List<CarCategoryDO> firstWordList = this.getByFirstWord(firstWord);
        for (CarCategoryDO temp :
                firstWordList) {
            if (level.equals(temp.getLevel())) {
                resultList.add(temp);
            }
        }
        return resultList;
    }

    public <T> List<T> listDistinctUseContains(List<T> li) {
        List<T> list = new ArrayList<>();
        int len = li.size();
        for (int i = 0; i < len; i++) {
            T temp = li.get(i);
            if (!list.contains(temp)) {
                list.add(temp);
            }
        }
        return list;
    }
    /*********************************20160309 lyj 新增接口 end*********************************************************/




    /*=====private========*/
    //将四级level以上的car，转化为其最后一级的car
    private List<CarCategoryDO> changeCarLevel4ToEnd(List<CarCategoryDO> list){
        while(true){
            Set<Integer> pidList = new HashSet<>();
            for(CarCategoryDO carData : list){
                pidList.add(carData.getId());
            }
            List<Integer> pidsList = new ArrayList(pidList);
            List<CarCategoryDO> findList = getCarListByPid(pidsList);

            if(null == findList || findList.isEmpty()){
                //找到最小level后跳出
                break;
            }
            list = findList;

            Integer level = list.get(0).getLevel();
            if(level.equals(ShareConstants.CAR_FINAL_LEVEL)){
                //到第6级跳出
                break;
            }
        }
        //power由小到大 year由大到小排序
        Collections.sort(list, new Comparator<CarCategoryDO>() {
            @Override
            public int compare(CarCategoryDO o1, CarCategoryDO o2) {
                if (o2.getYear().equals(o1.getYear())) {
                    return o1.getPower().compareTo(o2.getPower());
                }
                return o2.getYear().compareTo(o1.getYear());
            }
        });
        return list;
    }

    /***
     *  fullName拼写
     *  车型排序：国产在前，进口车在尾，合资在中间
     *  合资车有多个合资厂商时，车系多的靠前
     */
    private List<CarListBO> sortCarCompany(List<CarCategoryDO> carList){
        List<CarListBO> finalResultList = Lists.newArrayList();

        List<CarListBO> domesticCar = Lists.newArrayList();   //国产
        List<CarListBO> importCar = Lists.newArrayList();     //进口
        Map<String, List<CarListBO>> jointCarMap = Maps.newHashMap();//合资车按合资厂商分组
        List<List<CarListBO>> jointCarMapValues = Lists.newArrayList();
        for(CarCategoryDO carCategoryDO : carList){
            CarListBO finalCarBO = new CarListBO();
            finalCarBO = BdUtil.do2bo(carCategoryDO, CarListBO.class);
            //获得各个父Id
            Integer level = carCategoryDO.getLevel();
            if(level == ShareConstants.CAR_FINAL_LEVEL) {
                CarCategoryDO year = getCarByPrimaryId(carCategoryDO.getPid());
                CarCategoryDO power = getCarByPrimaryId(year.getPid());
                CarCategoryDO model = getCarByPrimaryId(power.getPid());
                CarCategoryDO series = getCarByPrimaryId(model.getPid());
                CarCategoryDO brand = getCarByPrimaryId(series.getPid());

                if (null != year) {
                    finalCarBO.setYearId(year.getId());
                }
                if (null != power) {
                    finalCarBO.setPowerId(power.getId());
                }
                if (null != model) {
                    finalCarBO.setModelId(model.getId());
                }
                if (null != series) {
                    finalCarBO.setSeriesId(series.getId());
                }
                if (null != brand) {
                    finalCarBO.setBrandId(brand.getId());
                }
            } else if (level == 3){
                Integer modelId = carCategoryDO.getId();
                CarCategoryDO series = getCarByPrimaryId(carCategoryDO.getPid());
                CarCategoryDO brand = getCarByPrimaryId(series.getPid());

                finalCarBO.setModelId(modelId);
                if (null != series) {
                    finalCarBO.setSeriesId(series.getId());
                }
                if (null != brand) {
                    finalCarBO.setBrandId(brand.getId());
                }
            } else if(level == 2) {
                Integer seriesId = carCategoryDO.getId();
                CarCategoryDO brand = getCarByPrimaryId(carCategoryDO.getPid());

                finalCarBO.setSeriesId(seriesId);
                if (null != brand) {
                    finalCarBO.setBrandId(brand.getId());
                }
            }else if(level == 1){
                finalCarBO.setBrandId(carCategoryDO.getId());
            }
            //获取车辆全称
            String fullName = this.getOldFullName(carCategoryDO);
            finalCarBO.setCarName(fullName);

            //company 排序,车型排序：国产在前，进口车在尾，合资在中间
            if (level == 1) {
                domesticCar.add(finalCarBO);
            } else {
                String importInfo = carCategoryDO.getImportInfo();
                if (importInfo == null || importInfo.trim().equals("")) continue;
                if (importInfo.trim().equals("国产")) {
                    domesticCar.add(finalCarBO);
                }
                if (importInfo.trim().equals("合资")) {
                    String key = carCategoryDO.getCompany().trim();
                    if (jointCarMap.containsKey(key)) {
                        jointCarMap.get(key).add(finalCarBO);
                    } else {
                        List<CarListBO> jointCarLists = Lists.newArrayList();
                        jointCarMap.put(key, jointCarLists);
                        jointCarMapValues.add(jointCarLists);
                        jointCarLists.add(finalCarBO);
                    }
                }
                if (importInfo.trim().equals("进口")) {
                    importCar.add(finalCarBO);
                }
            }
        }

        finalResultList.addAll(domesticCar);
        Collections.sort(jointCarMapValues, new Comparator<List<CarListBO>>() {
            public int compare(List<CarListBO> o1, List<CarListBO> o2) {
                return o2.size() - o1.size();//按list长度从大到小，即车系多的合资厂商在前
            }
        });
        for (List<CarListBO> jointCarList : jointCarMapValues) {
            finalResultList.addAll(jointCarList);
        }
        finalResultList.addAll(importCar);

        if(!finalResultList.isEmpty() && finalResultList.get(0).getLevel() == 1){
            //品牌排序
            Collections.sort(finalResultList, new Comparator<CarListBO>() {
                @Override
                public int compare(CarListBO o1, CarListBO o2) {
                    return o1.getFirstWord().toUpperCase().compareTo(o2.getFirstWord().toUpperCase());
                }
            });
        }

        return finalResultList;
    }



    //获取适配4级的车型名:level 1 2 3 6
    private String getOldFullName(CarCategoryDO carCategoryDO){
        Integer level = carCategoryDO.getLevel();
        String name = carCategoryDO.getName();
        if(level == 1){
            return name;
        }
        if(level == ShareConstants.CAR_FINAL_LEVEL){
            String fullName = carCategoryDO.getYear() + "款 "+name;
            return fullName;
        }

        StringBuilder fullName = new StringBuilder();
        //若为进口，名称为 进口+名称 否则 厂商名+名称 --------国产 不加厂商名
        String importInfo = carCategoryDO.getImportInfo();
        if ("进口".equals(importInfo)) {
            fullName.append(importInfo).append(" ");
        } else if ("合资".equals(importInfo)) {
            fullName.append(carCategoryDO.getCompany()).append(" ");
        }

        if(level == 2){
            fullName.append(name);
            return fullName.toString().trim();
        }

        if(level == 3){
            String seriesName = carCategoryDO.getSeries();
            if (! name.startsWith(seriesName)) {
                fullName.append(seriesName).append(" ").append(name);
            } else {
                fullName.append(name);
            }
            return fullName.toString().trim();

        }

        return name;
    }


}
