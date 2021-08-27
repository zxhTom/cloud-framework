package com.github.zxhtom.datasource.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.utils
 * @date 2021/8/27 14:33
 */
public class CollectionUtils {

    public static <T> List<List<T>> averageSize(List<T> list, int pageSize) {
        List<List<T>> listArray = new ArrayList<List<T>>();
        for (int i = 0; i < list.size(); i+=pageSize) {
            int toIndex = i + pageSize>list.size()?list.size():i+pageSize;
            listArray.add(list.subList(i, toIndex));
        }
        return listArray;
    }
}
