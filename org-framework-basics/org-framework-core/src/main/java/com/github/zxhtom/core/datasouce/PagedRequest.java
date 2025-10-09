package com.github.zxhtom.core.datasouce;

import lombok.Data;

/**
 * @author zxhtom
 * 10/6/25
 */
@Data
public class PagedRequest<T> {
    private T item;
    /**
     * 页码
     */
    private Integer pageNumber;
    /**
     * 每页笔数
     */
    private Integer pageSize;

}
