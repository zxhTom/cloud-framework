package com.github.zxhtom.datasource;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.github.zxhtom.datasource.mappers.method.InsertBatchMethod;
import com.github.zxhtom.datasource.mappers.method.UpdateBatchMethod;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource
 * @date 2021/8/27 14:08
 */
public class CustomizedSqlInjector extends DefaultSqlInjector {
    /**
     * 如果只需增加方法，保留mybatis plus自带方法，
     * 可以先获取super.getMethodList()，再添加add
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new InsertBatchMethod());
        methodList.add(new UpdateBatchMethod());
        return methodList;
    }
}
