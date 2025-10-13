package com.github.zxhtom.datasource.auto;

import com.github.zxhtom.datasource.intercepter.InterceptorFillParameterRegister;
import com.github.zxhtom.datasource.intercepter.RpcInterceptor;
import com.github.zxhtom.datasource.intercepter.impl.BaseModelFIllHandler;
import com.github.zxhtom.datasource.intercepter.impl.MultiParamFillHandler;
import com.github.zxhtom.datasource.typeHandler.EnumScanner;
import com.github.zxhtom.datasource.typeHandler.EnumTypeHandlerAutoRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.auto
 * @date 2021/9/29 16:30
 */
@Configuration
public class InterceptorConfiguration {

    @Bean
    public InterceptorFillParameterRegister interceptorFillParameterRegister() {
        InterceptorFillParameterRegister register = new InterceptorFillParameterRegister();
        register.register(new BaseModelFIllHandler());
        register.register(new MultiParamFillHandler());
        return register;
    }
    @Bean
    public RpcInterceptor rpcInterceptor() {
        return new RpcInterceptor();
    }
    @Bean
    @ConditionalOnMissingBean
    public EnumScanner enumScanner() {
        return new EnumScanner();
    }
    @Bean
    public EnumTypeHandlerAutoRegistrar enumTypeHandlerAutoRegistrar() {
        return new EnumTypeHandlerAutoRegistrar();
    }
}
