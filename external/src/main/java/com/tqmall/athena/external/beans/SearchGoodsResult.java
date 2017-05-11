package com.tqmall.athena.external.beans;

import lombok.Data;

import java.util.List;

/**
 * Created by huangzhangting on 16/9/19.
 */
@Data
public class SearchGoodsResult {
    private Integer num;
    private List<SearchGoodsBO> list;
}
