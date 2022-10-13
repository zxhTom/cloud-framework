package com.github.zxhtom.dynic.datasource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.zxhtom.dynic.datasource.aop.DataSourceContextHolder;
import com.github.zxhtom.dynic.datasource.aop.DatabaseList;
import com.github.zxhtom.dynic.datasource.aop.DynamicRoutingDataSource;
import com.github.zxhtom.dynic.datasource.mapper.DynicTestMapper;
import com.github.zxhtom.dynic.datasource.model.DynicTest;
import com.github.zxhtom.dynic.datasource.service.DatabaseListService;
import com.github.zxhtom.dynic.datasource.service.DynicTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zxhtom
 * 2022/9/27
 */
@Service
public class DynicTestServiceImpl implements DynicTestService {
    @Resource
    DynicTestMapper dynicTestMapper;
    @Resource
    DatabaseListService databaseListService;
    @Resource
    private DynamicRoutingDataSource dynamicRoutingDataSource;
    @Override
    public List<DynicTest> start() {
        return dynicTestMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public boolean switchDB(String datasourceId) {
        //切到默认数据源
        DataSourceContextHolder.removeDataSource();
        //找到所有的配置
        List<DatabaseList> databaseListList = databaseListService.list();

        if(!CollectionUtils.isEmpty(databaseListList)){
            for (DatabaseList d : databaseListList) {
                if(d.getFactoryCode().equals(datasourceId)){
                    //判断连接是否存在，不存在就创建
                    dynamicRoutingDataSource.checkCreateDataSource(d);
                    //切换数据源
                    DataSourceContextHolder.setDataSource(d.getFactoryCode());
                    return true;
                }
            }
        }
        return false;
    }
}
