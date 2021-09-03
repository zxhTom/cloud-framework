package com.github.zxhtom.core.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.zxhtom.core.constant.MaltcloudConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.config
 * @date 2021/9/3 9:44
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
        //实现一个请求到视图的映射，而无需书写controller
        registry.addViewController("/404").setViewName("forward:/maltcloud/error.html");
    }

    /**
     * 表单数据转换器
     */
    @Bean
    public AllEncompassingFormHttpMessageConverter getFormHttpMessageConverter() {
        return new AllEncompassingFormHttpMessageConverter();
    }

    /**
     * Json数据转换器
     */
    @Bean
    public MappingJackson2HttpMessageConverter getJsonHttpMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(MaltcloudConstant.COMMON_DATE_FORMAT);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON_UTF8, MediaType.TEXT_PLAIN));
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    /**
     * 定制消息转换器, 控制对象序列化格式
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(getFormHttpMessageConverter());
        converters.add(getJsonHttpMessageConverter());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //registry.addConverterFactory(new UniversalEnumConverterFactory());
        registry.addFormatter(new DateFormatter(MaltcloudConstant.DATEKEY_FORMAT_PATTERN));
        registry.addFormatterForFieldType(Date.class,new DateFormatter(MaltcloudConstant.DATEKEY_FORMAT_PATTERN));
    }
}
