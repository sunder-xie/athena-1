package com.tqmall.athena.test.dubboTest.car;

import com.tqmall.athena.client.car.CarCategoryService;
import com.tqmall.athena.common.utils.JsonUtil;
import com.tqmall.athena.domain.result.carcategory.CarCategoryDTO;
import com.tqmall.athena.test.BaseDubboTest;
import com.tqmall.core.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by lyj on 16/3/9.
 */
@Slf4j
public class DubboForStallTest extends BaseDubboTest {
    @Autowired
    private CarCategoryService carCategoryService;

    private void listDTOSuccess(Result<List<CarCategoryDTO>> result) {
        if (result.isSuccess()) {
            List<CarCategoryDTO> list = result.getData();

            System.out.println(list.size());
            for (CarCategoryDTO carCategoryDTO :
                    list) {
                System.out.println(carCategoryDTO.getId());
            }
            System.out.println(JsonUtil.objectToJsonStr(list));

            assertTrue(list.size() > 0);
        }
    }

    @Test
    public void testGetCarCategoryByPrimaryId() {
        System.out.println("=====================testGetCarCategoryByPrimaryId:");

        Integer id = 0;
        Result<CarCategoryDTO> result = carCategoryService.getCarCategoryByPrimaryId(id);

        System.out.println(result.isSuccess());
        if (result.isSuccess()) {
            CarCategoryDTO obj = result.getData();

            System.out.println(obj.getId());

            System.out.println(JsonUtil.objectToJsonStr(obj));
            assertTrue(obj != null);
        }

        System.out.println("=====================end:");
    }

    @Test
    public void testGetByPrimaryIdList() {
        System.out.println("=====================testGetCarParentsByCarId:");

        List<Integer> ids = new ArrayList<>();
        ids.add(48781);
        ids.add(48782);
        ids.add(48783);
        ids.add(48784);
        ids.add(48785);

        Result<List<CarCategoryDTO>> result = carCategoryService.getByPrimaryIdList(ids);

        System.out.println(result.isSuccess());

        listDTOSuccess(result);

        System.out.println("=====================end:");
    }

    @Test
    public void testGetByCarModels() {
        System.out.println("=====================testGetByCarModels:");

        List<Integer> otherModels = new ArrayList<>();
        otherModels.add(48781);
        otherModels.add(48782);
        otherModels.add(48783);
        otherModels.add(48784);
        otherModels.add(48785);
        List<String> brandNames = new ArrayList<>();
        brandNames.add("克莱斯勒");

        Result<List<CarCategoryDTO>> result = carCategoryService.getByCarModels(otherModels, brandNames);

        System.out.println(result.isSuccess());
        System.out.println(result.getMessage());

        listDTOSuccess(result);

        System.out.println("=====================end:");
    }

    @Test
    public void testGetCarCategoryByPid() {
        System.out.println("=====================testGetCarCategoryByPid:");
        Result<List<CarCategoryDTO>> result = carCategoryService.getCarCategoryByPid(1);

        System.out.println(result.isSuccess());
        System.out.println(result.getMessage());

        listDTOSuccess(result);

        System.out.println("=====================end:");
    }

    @Test
    public void testGetFirstWords() {
        System.out.println("=====================testGetFirstWords:");
        Result<List<String>> result = carCategoryService.getFirstWords();

        System.out.println(result.isSuccess());
        System.out.println(result.getMessage());

        if (result.isSuccess()) {
            List<String> list = result.getData();

            System.out.println(list.size());
            for (String string :
                    list) {
                System.out.println(string);
            }
        }

        System.out.println("=====================end:");
    }

    @Test
    public void testGetByFirstWord() {
        System.out.println("=====================testGetByFirstWord:");
        Result<List<CarCategoryDTO>> result = carCategoryService.getByFirstWord("");

        System.out.println(result.isSuccess());
        System.out.println(result.getMessage());

        listDTOSuccess(result);

        System.out.println("=====================end:");
    }

    @Test
    public void testGetByLevelList() {
        System.out.println("=====================testGetByLevelList:");
        List<Integer> levels = new ArrayList<>();
        levels.add(1);
        levels.add(2);

        Result<List<CarCategoryDTO>> result = carCategoryService.getByLevelList(levels);

        System.out.println(result.isSuccess());
        System.out.println(result.getMessage());

        listDTOSuccess(result);

        System.out.println("=====================end:");
    }

    @Test
    public void testGetByFirstWordLevel() {
        System.out.println("=====================testGetByFirstWordLevel:");
        String firstWord = "D";
        Integer level = -1;

        Result<List<CarCategoryDTO>> result = carCategoryService.getByFirstWordLevel(firstWord, level);

        System.out.println(result.isSuccess());
        System.out.println(result.getMessage());

        listDTOSuccess(result);

        System.out.println("=====================end:");
    }

    @Test
    public void testListQC() {
        List<Integer> otherModels = new ArrayList<>();
        otherModels.add(48781);
        otherModels.add(48781);
        otherModels.add(48781);
        otherModels.add(null);
        otherModels.add(48782);

        List<Integer> newList = listDistinctUseContains(otherModels);
        for (Integer i:
        newList) {
            System.out.println(i);
        }
    }

    public <T> List<T> listDistinctUseContains(List<T> li) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < li.size(); i++) {
            if (!list.contains(li.get(i))) {
                list.add(li.get(i));
            }
        }
        return list;
    }

}
