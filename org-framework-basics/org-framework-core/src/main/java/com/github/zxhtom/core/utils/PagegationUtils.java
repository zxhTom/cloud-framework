package com.github.zxhtom.core.utils;

import com.github.zxhtom.core.datasouce.PagedResult;
import com.github.zxhtom.core.exception.BusinessException;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.utils
 * @date 2021/10/22 13:38
 */
public class PagegationUtils {
    private static PagegationUtils util = new PagegationUtils();

    public static PagegationUtils getInstance(){
        return util;
    }


    public <T> void initOthers3P(PagedResult<T> result, List<T> userList, Long count) {
        if (result == null) {
            throw new BusinessException("分页参数不可空");
        }
        if (result.getPageSize() == null||result.getPageSize()<=0) {
            throw new BusinessException("pagesize参数不合法，请填写正整数");
        }
        result.setDatas(userList).setTotalRecords(count).setTotalPages(1+Double.valueOf(count / result.getPageSize()).intValue());
    }

    public Integer pageStart(Integer pageNumber, Integer pageSize) {
        if (pageNumber <= 0) {
            throw new BusinessException("pagenumber参数不合法，请填写正整数");
        }
        if (pageSize <= 0) {
            throw new BusinessException("pagesize参数不合法，请填写正整数");
        }
        return (pageNumber - 1) * pageSize;
    }
}
