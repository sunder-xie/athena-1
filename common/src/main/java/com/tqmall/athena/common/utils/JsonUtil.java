package com.tqmall.athena.common.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * success!
 * Created by zxg on 15/09/22.
 */
@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //关闭时间戳输出
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        //使用自己的时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(sdf);
    }

    public static String objectToJsonStr(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (IOException e) {
            log.error("object can not objectTranslate to json", e);
        }
        return null;
    }

    public static <T> T jsonStrToObject(String json, Class<T> cls) {
        try {
            return objectMapper.readValue(json, cls);
        } catch (IOException e) {
            log.error("json cant be objectTranslate to object, json:"+json, e);
            return null;
        }
    }

    /*
    *  json字符串，转换成集合
    *  content：本身必须是json数组
    *  collectionClass：集合class 例如 List.class
    *  elementClass：元素class 例如 User.class
    * */
    public static <T> T jacksonToCollection(String content, Class<? extends Collection> collectionClass,
                                            Class<?> elementClass) {
        try {
            JavaType javaType = getCollectionType(collectionClass, elementClass);
            return objectMapper.readValue(content, javaType);
        }catch (Exception e){
            log.error("jacksonToCollection error, content:"+content, e);
        }
        return null;
    }

    /*
    *  获得java集合类型，json在转化成java集合时需要用到
    *  collectionClass：集合class 例如 List.class
    *  elementClass：元素class 例如 User.class
    * */
    public static JavaType getCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }

    //==================特殊常用的=======================

    public static <T> List<T> jsonStrToList(String jsonStr, Class<?> clazz) {

        List<T> list = Lists.newArrayList();

        try {
            // 指定容器结构和类型（这里是ArrayList和clazz）
            TypeFactory t = TypeFactory.defaultInstance();
            list = objectMapper.readValue(jsonStr,
                    t.constructCollectionType(ArrayList.class, clazz));


        } catch (IOException e) {
            log.error("反序列化序列化attributes，从Json到List报错", e);
        }
        return list;
    }

    public static Map jsonStrToMap(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, HashMap.class);
        } catch (IOException e) {
            log.error("反序列化序列化attributes，从Json到HashMap报错", e);
        }
        return new HashMap();
    }


    /*
    *  JsonNode：就是json数组对象
    *  collectionClass：集合class 例如 List.class
    *  elementClass：元素class 例如 User.class
    * */
    public static <T> T jsonNodeToCollection(JsonNode jsonNode, Class<? extends Collection> collectionClass,
                                             Class<?> elementClass) {
        try {
            JavaType javaType = getCollectionType(collectionClass, elementClass);
            return objectMapper.readValue(jsonNode, javaType);
        }catch (Exception e){
            log.error("jsonNodeToCollection error", e);
        }
        return null;
    }

    /*
    *  将json字符串转换成json对象
    *
    *  JsonNode：就是json对象，包括数组、或单个对象
    *  content：json字符串
    *
    *  可以通过 jsonNode.findValue("pro") 查找属性值，返回值还是 JsonNode
    * */
    public static JsonNode getJsonNode(String content) {
        try {
            return objectMapper.readTree(content);
        } catch (IOException e) {
            log.error("getJsonNode error, content:"+content, e);
        }
        return null;
    }


}
