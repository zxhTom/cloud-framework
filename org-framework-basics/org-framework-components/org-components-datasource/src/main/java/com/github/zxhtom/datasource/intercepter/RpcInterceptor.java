package com.github.zxhtom.datasource.intercepter;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zxhtom.datasource.model.BaseModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.intercepter
 * @date 2021/9/29 15:31
 */
@Intercepts({
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class RpcInterceptor implements Interceptor {
    @Autowired
    InterceptorFillParameterRegister register;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();
        final MappedStatement mappedStatement = (MappedStatement) args[0];
        final Object parameter = args[1];
        String id = mappedStatement.getId();
        String className = StringUtils.EMPTY;
        String regex = "(?<className>.*)\\.\\w+";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(id);
        if (matcher.find()) {
            className = matcher.group("className");
        }
        Class<?> clazz = Class.forName(className);
        if (BaseMapper.class.isAssignableFrom(clazz)) {
            return invocation.proceed();
        }
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        List<AbstractFillHandler> abHandlerList = register.getAbHandlerList();
        for (AbstractFillHandler handler : abHandlerList) {
            if (!handler.support(parameter)) {
                continue;
            }
            handler.fill(sqlCommandType);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
