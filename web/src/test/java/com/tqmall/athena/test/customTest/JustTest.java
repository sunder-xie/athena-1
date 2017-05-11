package com.tqmall.athena.test.customTest;

import com.google.common.collect.Lists;
import com.tqmall.athena.bean.entity.vehicle.VehicleCategoryDO;
import com.tqmall.athena.common.redis.RedisKeyBean;
import com.tqmall.athena.common.utils.JsonUtil;
import org.junit.Test;

import java.util.*;

/**
 * Created by zxg on 15/9/21.
 * 普通的test
 */
public class JustTest {

    @Test
    public void testCompare(){
        String a = "1999L";
        String b = "2000L";

        Integer result = a.compareTo(b);
        System.out.println(result);

//        Integer c = Integer.valueOf(a);
//        Integer d = Integer.valueOf(b);
//        Integer IntResult = c.compareTo(d);

//        System.out.println(IntResult);


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);


        List<Integer> subList = list.subList(0, 3);
        System.out.println(subList);
    }

    @Test
    public void testSort(){
        List<Integer> list = Lists.newArrayList(3,6,1,3,546,46,462,1,2);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for(Integer q : list){
            System.out.println(q);
        }
    }

    @Test
    public void testList(){
        Map<String,Integer> map = new HashMap<>();
        map.put("one", 123);
        map.put("two",456);

        List<Map<String,Integer>> list = Lists.newArrayList(map);
        for(Map<String,Integer> map1 : list){
            System.out.println(map1.get("one"));
            System.out.println(map1.get("two"));
        }
        testListPrivate(list);
        for(Map<String,Integer> map1 : list){
            System.out.println(map1.get("one"));
            System.out.println(map1.get("two"));
        }
    }

    private void testListPrivate(List<Map<String,Integer>> list){
        for(Map<String,Integer> map : list){
            map.put("one", map.get("one") + 2);
        }
    }

    @Test
    public void testJackSon(){
        TestBean testBean = new TestBean();
        testBean.setOne("one");
        testBean.setTwo("two");
        testBean.setThree("three");
        //bean to json
        String json = JsonUtil.objectToJsonStr(testBean);
        System.out.println("bean to json:"+json);
        //json to bean
        TestBean test1 = JsonUtil.jsonStrToObject(json,TestBean.class);
        System.out.println("json to bean:"+test1.getOne());

        //list to json
        TestBean testBean1 = new TestBean();
        testBean1.setOne("one1");
        testBean1.setTwo("two2");
        testBean1.setThree("three3");
        List<TestBean> list = Lists.newArrayList(testBean,testBean1);
        String listJson = JsonUtil.objectToJsonStr(list);
        System.out.println("list to json:"+listJson);

        //json to list
        List<TestBean> list1 = JsonUtil.jsonStrToList(listJson,TestBean.class);
        System.out.println("json to list:"+list1.size());

        for(TestBean testBean2 : list1){
            System.out.println("here:"+testBean2.getThree());
        }

        //json强转list
        List<TestBean> list2 = null;
        try {
            list2 = JsonUtil.jacksonToCollection(listJson, List.class, TestBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("1强转json to list:"+list2.size());

        for(TestBean testBean2 : list2){
            System.out.println("here:"+testBean2.getThree());
        }

        List<TestBean> list3 =  JsonUtil.jsonStrToObject(listJson, List.class);
        System.out.println("2强转json to list:"+list3.size());

        for(TestBean testBean2 : list3){
            System.out.println("here:"+testBean2.getThree());
        }
    }


    @Test
    public void test1111(){
        Integer pid = 500000;
        System.out.println(pid == VehicleCategoryDO.INIT_ID);


        String code = "   ";
        System.out.println(code.replace(" ", "").toUpperCase());

    }


    @Test
    public void test_11(){
        Integer goodsId = 1000;
        String ks = String.format(RedisKeyBean.CAR_MODELS_BY_GOODS_SERIES_ID, goodsId, -1);
        String pattern = ks.replace("-1", "*");
        int idx = ks.lastIndexOf("0");

        System.out.println(pattern);
    }




}
