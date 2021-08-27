package com.github.zxhtom.datasource.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zxhtom.datasource.properties.MybatisProperties;
import com.github.zxhtom.datasource.utils.CollectionUtils;
import com.github.zxhtom.web.context.ApplicationContextUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.mappers
 * @date 2021/8/27 13:59
 * @description 扩展通用功能 。 换成tk也同样存在项目目录
 */
public interface MaltcloudMapper<T> extends BaseMapper<T> {

    /**
     * 自定义批量插入
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    @Deprecated
    int insertBatch(@Param("list") List<T> list);

    default int insertBatchInTurn(@Param("list") List<T> list,Integer size){
        MybatisProperties mybatisProperties = ApplicationContextUtil.getApplicationContext().getBean(MybatisProperties.class);
        Integer maxBatchSize = mybatisProperties.getMaxBatchSize();
        Integer result = 0;
        size = size> maxBatchSize?maxBatchSize:size;
        List<List<T>> subList = CollectionUtils.averageSize(list, size);
        for (List<T> sourceList : subList) {
            result+=insertBatch(sourceList);
        }
        return result;
    }
    /**
     * 自定义批量更新，条件为主键
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    @Deprecated
    int updateBatch(@Param("list") List<T> list);

    default int updateBatchInTurn(@Param("list") List<T> list,Integer size){
        MybatisProperties mybatisProperties = ApplicationContextUtil.getApplicationContext().getBean(MybatisProperties.class);
        Integer maxBatchSize = mybatisProperties.getMaxBatchSize();
        Integer result = 0;
        size = size> maxBatchSize?maxBatchSize:size;
        List<List<T>> subList = CollectionUtils.averageSize(list, size);
        for (List<T> sourceList : subList) {
            result+=updateBatch(sourceList);
        }
        return result;
    }
}
