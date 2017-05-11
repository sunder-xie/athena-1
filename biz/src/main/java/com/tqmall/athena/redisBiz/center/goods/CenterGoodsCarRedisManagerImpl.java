package com.tqmall.athena.redisBiz.center.goods;

import com.tqmall.athena.bean.bizBean.center.CenterGoodsCarDetailBO;
import com.tqmall.athena.bean.common.ShareConstantsUtil;
import com.tqmall.athena.bean.entity.center.goods.*;
import com.tqmall.athena.common.redis.RedisClientTemplate;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.dal.mapper.center.goods.CenterGoodsCarDOMapper;
import com.tqmall.athena.dal.mapper.center.goods.CenterGoodsCarPictureDOMapper;
import com.tqmall.athena.dal.mapper.center.goods.CenterGoodsCarSubjoinDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyj on 16/2/17.
 */
@Service
public class CenterGoodsCarRedisManagerImpl implements CenterGoodsCarRedisManager {
    @Autowired
    private RedisClientTemplate redisClient;

    @Autowired
    private CenterGoodsRedisManager goodsRedisManager;

    @Autowired
    private CenterGoodsAttrRedisManager attrRedisManager;

    @Autowired
    private CenterGoodsCarDOMapper centerGoodsCarDOMapper;
    @Autowired
    private CenterGoodsCarPictureDOMapper pictureDOMapper;
    @Autowired
    private CenterGoodsCarSubjoinDOMapper subjoinDOMapper;


    @Override
    public CenterGoodsCarDetailBO getGoodsCarDetailByGoodsCar(Integer goodsId, Integer carId) {
        String key = String.format(RedisKeyBean.CENTER_GC_DETAIL_GID_CID_KEY, goodsId, carId);
        String redisStr = redisClient.get(key);
        if (redisClient.isNone(redisStr)) {
            return null;
        }

        if (redisStr != null) {
            return JsonUtil.jsonStrToObject(redisStr, CenterGoodsCarDetailBO.class);
        }

        List<CenterGoodsCarDO> goodsCarDOList = getGoodsCarByGoodsCar(goodsId, carId);
        CenterGoodsCarDetailBO detailBO = null;
        if (!goodsCarDOList.isEmpty()) {
            detailBO = handelGoodsDetail(goodsCarDOList.get(0), null);
        }
        if (detailBO == null) {
            redisClient.setNone(key);
        } else {
            redisClient.lazySet(key, detailBO, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return detailBO;
    }

    @Override
    public List<CenterGoodsCarDO> getGoodsCarByGoodsCar(Integer goodsId, Integer carId) {
        String key = String.format(RedisKeyBean.CENTER_GC_GID_CID_KEY, goodsId, carId);
        String redisStr = redisClient.get(key);
        if (redisClient.isNone(redisStr)) {
            return new ArrayList<>();
        }

        if (redisStr != null) {
            return JsonUtil.jsonStrToList(redisStr, CenterGoodsCarDO.class);
        }

        CenterGoodsCarDO centerGoodsCarDO = new CenterGoodsCarDO();
        centerGoodsCarDO.setGoodsId(goodsId);
        centerGoodsCarDO.setCarId(carId);
        List<CenterGoodsCarDO> list = centerGoodsCarDOMapper.selectByDO(centerGoodsCarDO);
        if (list.isEmpty()) {
            redisClient.setNone(key);
        } else {
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

    @Override
    public CenterGoodsCarPictureDO getGoodsCarPictureById(Integer id) {
        if (id == null || id < 1) {
            return null;
        }
        String key = String.format(RedisKeyBean.CENTER_GC_PIC_ID_KEY, id);
        String redisStr = redisClient.get(key);
        if (redisClient.isNone(redisStr)) {
            return null;
        }
        if (redisStr != null) {
            return JsonUtil.jsonStrToObject(redisStr, CenterGoodsCarPictureDO.class);
        }
        CenterGoodsCarPictureDO pictureDO = pictureDOMapper.selectByPrimaryKey(id);
        if (pictureDO == null) {
            redisClient.setNone(key);
        } else {
            redisClient.lazySet(key, pictureDO, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return pictureDO;
    }

    @Override
    public CenterGoodsCarSubjoinDO getGoodsCarSubjoinById(Integer id) {
        if (id == null || id < 1) {
            return null;
        }
        String key = String.format(RedisKeyBean.CENTER_GC_SUB_ID_KEY, id);
        String redisStr = redisClient.get(key);
        if (redisClient.isNone(redisStr)) {
            return null;
        }
        if (redisStr != null) {
            return JsonUtil.jsonStrToObject(redisStr, CenterGoodsCarSubjoinDO.class);
        }
        CenterGoodsCarSubjoinDO subjoinDO = subjoinDOMapper.selectByPrimaryKey(id);
        if (subjoinDO == null) {
            redisClient.setNone(key);
        } else {
            redisClient.lazySet(key, subjoinDO, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return subjoinDO;
    }

    @Override
    public CenterGoodsCarSubjoinDO getSubjoinByGoodsModel(Integer goodsId, Integer modelId) {
        String key = String.format(RedisKeyBean.CENTER_GC_SUB_GID_MID_KEY, goodsId, modelId);
        String redisStr = redisClient.get(key);
        if (redisClient.isNone(redisStr)) {
            return null;
        }
        if (redisStr != null) {
            return JsonUtil.jsonStrToObject(redisStr, CenterGoodsCarSubjoinDO.class);
        }

        CenterGoodsCarDO centerGoodsCarDO = new CenterGoodsCarDO();
        centerGoodsCarDO.setGoodsId(goodsId);
        centerGoodsCarDO.setModelId(modelId);
        List<CenterGoodsCarDO> goodsCarDOList = centerGoodsCarDOMapper.selectByDO(centerGoodsCarDO);
        if (goodsCarDOList.isEmpty()) {
            redisClient.setNone(key);
            return null;
        }

        CenterGoodsCarSubjoinDO subjoinDO = getGoodsCarSubjoinById(goodsCarDOList.get(0).getSubjoinId());
        if (subjoinDO == null) {
            redisClient.setNone(key);
        } else {
            redisClient.lazySet(key, subjoinDO, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return subjoinDO;
    }

    @Override
    public List<CenterGoodsCarPictureDO> getPictureByPicNum(String picNum) {
        String key = String.format(RedisKeyBean.CENTER_GCP_PIC_NUM_KEY, picNum);
        String redisStr = redisClient.get(key);
        if (redisClient.isNone(redisStr)) {
            return new ArrayList<>();
        }
        if (redisStr != null) {
            return JsonUtil.jsonStrToList(redisStr, CenterGoodsCarPictureDO.class);
        }

        List<CenterGoodsCarPictureDO> list = pictureDOMapper.selectByPicNum(picNum);
        if (list.isEmpty()) {
            redisClient.setNone(key);
        } else {
            redisClient.lazySet(key, list, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return list;
    }

    @Override
    public List<CenterGoodsCarDetailBO> getGcDetailByPicNumAndCar(String picNum, Integer carId) {
        String key = String.format(RedisKeyBean.CENTER_GC_DETAIL_PIC_NUM_CAR_KEY, picNum, carId);
        String redisStr = redisClient.get(key);
        if (redisClient.isNone(redisStr)) {
            return new ArrayList<>();
        }
        if (redisStr != null) {
            return JsonUtil.jsonStrToList(redisStr, CenterGoodsCarDetailBO.class);
        }

        List<CenterGoodsCarPictureDO> pictureDOList = getPictureByPicNum(picNum);
        if (pictureDOList.isEmpty()) {
            redisClient.setNone(key);
            return new ArrayList<>();
        }

        List<CenterGoodsCarDO> goodsCarDOList = getGoodsCarByGoodsCar(null, carId);
        if (goodsCarDOList.isEmpty()) {
            redisClient.setNone(key);
            return new ArrayList<>();
        }

        List<CenterGoodsCarDetailBO> detailBOList = new ArrayList<>();
        for (CenterGoodsCarPictureDO pictureDO : pictureDOList) {
            for (CenterGoodsCarDO goodsCarDO : goodsCarDOList) {
                if (pictureDO.getId().equals(goodsCarDO.getPicId())) {
                    CenterGoodsCarDetailBO detailBO = handelGoodsDetail(goodsCarDO, null);
                    if (detailBO != null) {
                        detailBOList.add(detailBO);
                    }
                }
            }
        }

        if (detailBOList.isEmpty()) {
            redisClient.setNone(key);
        } else {
            redisClient.lazySet(key, detailBOList, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return detailBOList;
    }

    //by zxg 16.10.27
    @Override
    public List<CenterGoodsCarDetailBO> getGcDetailByCatAndCar(Integer thirdCatId, Integer carId) {
        String key = String.format(RedisKeyBean.CENTER_GC_DETAIL_CATE_CAR_KEY, thirdCatId, carId);
        String redisStr = redisClient.get(key);
        if (redisClient.isNone(redisStr)) {
            return new ArrayList<>();
        }
        if (redisStr != null) {
            return JsonUtil.jsonStrToList(redisStr, CenterGoodsCarDetailBO.class);
        }

        List<CenterGoodsCarDetailBO> resultList = new ArrayList<>();

        List<CenterGoodsCarDO> goodsCarList = getGoodsCarByGoodsCar(null, carId);
        List<CenterGoodsDO> goodsDOList = goodsRedisManager.getGoodsByThirdCatId(thirdCatId);
        Integer goodsCarSize = goodsCarList.size();
        Integer goodsSize = goodsDOList.size();

        for (int i = 0; i < goodsCarSize; i++) {
            CenterGoodsCarDO centerGoodsCarDO = goodsCarList.get(i);
            Integer goodsId = centerGoodsCarDO.getGoodsId();
            for (int j = 0; j < goodsSize; j++) {
                CenterGoodsDO goodsDO = goodsDOList.get(j);
                if (goodsId.equals(goodsDO.getId())) {
                    //生产对象
                    CenterGoodsCarDetailBO detailBO = handelGoodsDetail(centerGoodsCarDO, goodsDO);
                    resultList.add(detailBO);
                }
            }
        }
        if (resultList.isEmpty()) {
            redisClient.setNone(key);
        } else {
            redisClient.lazySet(key, resultList, RedisKeyBean.RREDIS_EXP_DAY);
        }
        return resultList;
    }

    /*=======私有方法=============*/
    //根据goods_car 和goods 本身组装详情
    private CenterGoodsCarDetailBO handelGoodsDetail(CenterGoodsCarDO goodsCarDO, CenterGoodsDO goodsDO) {
        CenterGoodsCarDetailBO detailBO = new CenterGoodsCarDetailBO();
        if (goodsCarDO == null) {
            return detailBO;
        }
        if (goodsDO == null) {
            goodsDO = goodsRedisManager.getGoodsByPrimaryId(goodsCarDO.getGoodsId());
        }
        //拼接goods
        if (goodsDO != null) {
            detailBO.setOeNumber(goodsDO.getOeNumber());
            detailBO.setPartName(goodsDO.getPartName());
            detailBO.setBrandId(goodsDO.getBrandId());
            detailBO.setBrandName(goodsDO.getBrandName());
            detailBO.setGoodsPic(goodsDO.getGoodsPic());
            detailBO.setGoodsFormat(goodsDO.getGoodsFormat());
        }

        detailBO.setId(goodsCarDO.getId());
        detailBO.setGoodsId(goodsCarDO.getGoodsId());
        detailBO.setCarId(goodsCarDO.getCarId());
        detailBO.setModelId(goodsCarDO.getModelId());
        //组装pic数据
        handelGoodsDetailFromPic(goodsCarDO.getPicId(), detailBO);
        //组装subjoin数据
        handelGoodsDetailFromSubjoin(goodsCarDO.getSubjoinId(), detailBO);
        //拼接属性
        handelAttrSimple(goodsCarDO.getGoodsId(), detailBO);


        return detailBO;
    }

    private void handelAttrSimple(Integer goodsId, CenterGoodsCarDetailBO detailBO) {
        List<CenterGoodsAttrDO> attrDOList = attrRedisManager.getGoodsAttrByGoodsId(goodsId);
        StringBuilder stringBuilder = new StringBuilder();

        Integer size = attrDOList.size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(attrDOList.get(i).getAttrValue());
            if (i < size - 1) {
                //不是最后一个
                stringBuilder.append(",");
            }
        }
        detailBO.setAttr_simple(stringBuilder.toString());
    }

    private void handelGoodsDetailFromPic(Integer picId, CenterGoodsCarDetailBO detailBO) {
        CenterGoodsCarPictureDO pictureDO = getGoodsCarPictureById(picId);
        if (pictureDO != null) {
            detailBO.setEpcIndex(pictureDO.getEpcIndex());
            detailBO.setEpcPicNum(pictureDO.getEpcPicNum());
            String epcPic = pictureDO.getEpcPic();

            List<String> picList = new ArrayList<>();
            if (!StringUtils.isEmpty(epcPic)) {
                String[] pics = epcPic.split(",");
                for (String pic : pics) {
                    picList.add(ShareConstantsUtil.getImgUrl(pic));
                }
            }
            detailBO.setEpcPicList(picList);
        }
    }

    private void handelGoodsDetailFromSubjoin(Integer subjoinId, CenterGoodsCarDetailBO detailBO) {
        CenterGoodsCarSubjoinDO subjoinDO = getGoodsCarSubjoinById(subjoinId);
        if (subjoinDO != null) {
            detailBO.setGoodsCarRemarks(subjoinDO.getGoodsCarRemarks());
            detailBO.setApplicationAmount(subjoinDO.getApplicationAmount());
        }
    }


}
