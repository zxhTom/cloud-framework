package com.github.zxhtom.datasource.mappers;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zxhtom.core.datasouce.PagedRequest;
import com.github.zxhtom.datasource.model.PageParam;
import com.github.zxhtom.datasource.model.SortablePageParam;
import com.github.zxhtom.datasource.model.SortingField;
import com.github.zxhtom.datasource.properties.MybatisProperties;
import com.github.zxhtom.datasource.utils.CollectionUtils;
import com.github.zxhtom.datasource.utils.MyBatisUtils;
import com.github.zxhtom.web.context.ApplicationContextUtil;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
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

    default T selectOne(String field, Object value) {
        return selectOne(new QueryWrapper<T>().eq(field, value));
    }

    default T selectOne(SFunction<T, ?> field, Object value) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default T selectOne(String field1, Object value1, String field2, Object value2) {
        return selectOne(new QueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    default T selectOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    default T selectOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2,
                        SFunction<T, ?> field3, Object value3) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2).eq(field3, value3));
    }

    /**
     * 获取满足条件的第 1 条记录
     *
     * 目的：解决并发场景下，插入多条记录后，使用 selectOne 会报错的问题
     *
     * @param field 字段名
     * @param value 字段值
     * @return 实体
     */
    default T selectFirstOne(SFunction<T, ?> field, Object value) {
        // 如果明确使用 MySQL 等场景，可以考虑使用 LIMIT 1 进行优化
        List<T> list = selectList(new LambdaQueryWrapper<T>().eq(field, value));
        return CollUtil.getFirst(list);
    }

    default T selectFirstOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        List<T> list = selectList(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2));
        return CollUtil.getFirst(list);
    }

    default T selectFirstOne(SFunction<T,?> field1, Object value1, SFunction<T,?> field2, Object value2,
                             SFunction<T,?> field3, Object value3) {
        List<T> list = selectList(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2).eq(field3, value3));
        return CollUtil.getFirst(list);
    }


    default Integer selectCount() {
        return selectCount(new QueryWrapper<>());
    }

    default Integer selectCount(String field, Object value) {
        return selectCount(new QueryWrapper<T>().eq(field, value));
    }

    default Integer selectCount(SFunction<T, ?> field, Object value) {
        return selectCount(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList() {
        return selectList(new QueryWrapper<>());
    }

    default List<T> selectList(String field, Object value) {
        return selectList(new QueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(SFunction<T, ?> field, Object value) {
        return selectList(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(String field, Collection<?> values) {
        if (CollUtil.isEmpty(values)) {
            return CollUtil.newArrayList();
        }
        return selectList(new QueryWrapper<T>().in(field, values));
    }

    default List<T> selectList(SFunction<T, ?> field, Collection<?> values) {
        if (CollUtil.isEmpty(values)) {
            return CollUtil.newArrayList();
        }
        return selectList(new LambdaQueryWrapper<T>().in(field, values));
    }

    default List<T> selectList(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        return selectList(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    default Page<T> selectPage(SortablePageParam pageParam, @Param("ew") Wrapper<T> queryWrapper) {
        return selectPage(pageParam, pageParam.getSortingFields(), queryWrapper);
    }

    default Page<T> selectPage(PagedRequest pageParam, @Param("ew") Wrapper<T> queryWrapper) {
        return selectPage(pageParam, null, queryWrapper);
    }

    default Page<T> selectPage(PagedRequest pageParam, Collection<SortingField> sortingFields, @Param("ew") Wrapper<T> queryWrapper) {
        // 特殊：不分页，直接查询全部
        if (PageParam.PAGE_SIZE_NONE.equals(pageParam.getPageSize())) {
            MyBatisUtils.addOrder(queryWrapper, sortingFields);
            List<T> list = selectList(queryWrapper);
            return new Page<T>().setRecords(list).setTotal(list.size());
        }

        // MyBatis Plus 查询
        IPage<T> mpPage = MyBatisUtils.buildPage(pageParam, sortingFields);
        selectPage(mpPage, queryWrapper);
        // 转换返回
        return new Page<T>().setRecords(mpPage.getRecords()).setTotal(mpPage.getTotal());
    }
}
