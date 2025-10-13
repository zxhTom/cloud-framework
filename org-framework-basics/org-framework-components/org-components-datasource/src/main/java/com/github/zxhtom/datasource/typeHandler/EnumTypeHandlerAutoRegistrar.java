package com.github.zxhtom.datasource.typeHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 枚举类型处理器自动注册器 - 修正版
 */
@Component
public class EnumTypeHandlerAutoRegistrar implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(EnumTypeHandlerAutoRegistrar.class);

    @Autowired(required = false)
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private EnumScanner enumScanner;

    @Value("${mybatis.enum-scan-packages:com.github.zxhtom}")
    private String[] scanPackages;

    @Value("${mybatis.auto-register-enums:true}")
    private boolean autoRegister;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!autoRegister || sqlSessionFactory == null) {
            logger.info("Auto registration of enum type handlers is disabled or SqlSessionFactory not available");
            return;
        }

        try {
            registerEnumTypeHandlers();
            logger.info("Successfully registered enum type handlers");
        } catch (Exception e) {
            logger.error("Failed to register enum type handlers", e);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void registerEnumTypeHandlers() {
        Configuration configuration = sqlSessionFactory.getConfiguration();
        Set<Class<?>> enumClasses = enumScanner.scanEnums(scanPackages);

        int registeredCount = 0;
        for (Class<?> enumClass : enumClasses) {
            try {
                if (enumClass.isEnum()) {
                    // 使用工厂创建 TypeHandler 实例
                    TypeHandler typeHandler = EnumTypeHandlerFactory.getTypeHandler((Class<Enum>) enumClass);
                    configuration.getTypeHandlerRegistry().register(enumClass, typeHandler);
                    registeredCount++;
                    logger.debug("Registered TypeHandler for enum: {}", enumClass.getName());
                }
            } catch (Exception e) {
                logger.warn("Failed to register TypeHandler for enum: {}", enumClass.getName(), e);
            }
        }

        logger.info("Auto-registered {} enum type handlers from packages: {}",
                registeredCount, String.join(", ", scanPackages));
    }

    /**
     * 手动注册单个枚举类
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void registerEnumTypeHandler(Class<? extends Enum> enumClass) {
        if (sqlSessionFactory == null) {
            throw new IllegalStateException("SqlSessionFactory is not available");
        }

        try {
            Configuration configuration = sqlSessionFactory.getConfiguration();
            TypeHandler typeHandler = EnumTypeHandlerFactory.getTypeHandler(enumClass);
            configuration.getTypeHandlerRegistry().register(enumClass, typeHandler);
            logger.info("Manually registered TypeHandler for enum: {}", enumClass.getName());
        } catch (Exception e) {
            logger.error("Failed to manually register TypeHandler for enum: {}", enumClass.getName(), e);
            throw new RuntimeException("Failed to register enum type handler", e);
        }
    }
}
