package com.github.zxhtom.core.datasouce;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.datasouce
 * @date 2021/10/22 13:34
 */
@Data
@Accessors(chain = true)
public class PagedResult<T> {
    /**
     * 总笔数
     */
    private Long totalRecords;
    /**
     * 总页数
     */
    private Integer totalPages;
    /**
     * 页码
     */
    private Integer pageNumber;
    /**
     * 每页笔数
     */
    private Integer pageSize;
    /**
     * 分页数据
     */
    private List<T> datas = new ArrayList<>();

    public PagedResult(Integer pageNumber,Integer pageSize){
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
