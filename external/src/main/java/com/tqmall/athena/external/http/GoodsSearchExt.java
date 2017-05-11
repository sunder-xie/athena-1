package com.tqmall.athena.external.http;

import com.tqmall.athena.common.bean.HttpClientResult;
import com.tqmall.athena.common.utils.HttpClientUtil;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.external.beans.SearchGoodsResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhangting on 16/9/19.
 */
@Slf4j
@Component
public class GoodsSearchExt {
    @Value("${search.host}")
    private String searchHost;

    /**
     * 根据类目、品牌、车型分类id，查询商品数据
     *
     * @param catIds 类目id，多个值以英文逗号隔开，任意级别
     * @param brandIds 品牌id，多个值以英文逗号隔开
     * @param carId 车型分类id，任意级别
     * @param page 页码 从 1 开始
     * @param rows 每页条数
     * @return
     */
    public SearchGoodsResult queryGoods(String catIds, String brandIds, Integer carId, Integer page, Integer rows){
        if(page==null || page<1){
            page = 1;
        }
        if(rows==null || rows<1){
            rows = 10;
        }
        int start = (page - 1) * rows;

        List<NameValuePair> pairList = new ArrayList<>();
        pairList.add(new BasicNameValuePair("start", start+""));
        pairList.add(new BasicNameValuePair("rows", rows+""));

        if(!StringUtils.isEmpty(catIds)) {
            pairList.add(new BasicNameValuePair("catId", catIds));
        }
        if(!StringUtils.isEmpty(brandIds)){
            pairList.add(new BasicNameValuePair("brandId", brandIds));
        }
        if(carId != null){
            pairList.add(new BasicNameValuePair("carType", carId.toString()));
        }

        String url = searchHost + "/elasticsearch/goods/convert";

        HttpClientResult clientResult = HttpClientUtil.getUrl(url, pairList);
        if(clientResult != null && !StringUtils.isEmpty(clientResult.getResult())){
            JsonNode jsonNode = JsonUtil.getJsonNode(clientResult.getResult());
            if(jsonNode != null){
                JsonNode response = jsonNode.findValue("response");
                if(response != null){
                    return JsonUtil.jsonStrToObject(response.toString(), SearchGoodsResult.class);
                }
            }
        }

        log.info("queryGoods failed, result:{}", JsonUtil.objectToJsonStr(clientResult));
        return null;
    }
}
