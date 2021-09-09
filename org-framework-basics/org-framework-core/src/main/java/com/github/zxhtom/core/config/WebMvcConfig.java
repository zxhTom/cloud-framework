package com.github.zxhtom.core.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.zxhtom.core.constant.MaltcloudConstant;
import com.github.zxhtom.core.converter.BaseEnumConverterFactory;
import com.github.zxhtom.core.date.MultiDateFormat;
import com.github.zxhtom.core.enums.BaseEnum;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.config
 * @date 2021/9/3 9:44
 */
@Configuration
@EnableWebMvc
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

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "dateformat")
    public MultiDateFormat multiDateFormat() {
        return new MultiDateFormat();
    }
    /**
     * Json数据转换器
     */
    @Bean
    public MappingJackson2HttpMessageConverter getJsonHttpMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(multiDateFormat());
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON_UTF8, MediaType.TEXT_PLAIN));
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Bean
    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
        XmlMapper objectMapper = new XmlMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(multiDateFormat());
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2XmlHttpMessageConverter jsonXmlConverter = new MappingJackson2XmlHttpMessageConverter();
        //jsonXmlConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON_UTF8, MediaType.TEXT_PLAIN));
        jsonXmlConverter.setObjectMapper(objectMapper);
        return jsonXmlConverter;
    }
    /**
     * 定制消息转换器, 控制对象序列化格式
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //converters.clear();
        /*converters.add(getFormHttpMessageConverter());
        converters.add(getJsonHttpMessageConverter());
        converters.add(mappingJackson2XmlHttpMessageConverter());*/
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new BaseEnumConverterFactory());
        //registry.addFormatterForFieldType(Date.class,new DateFormatter(MaltcloudConstant.MONTHKEY_FORMAT_PATTERN));
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.useJaf(false).favorPathExtension(false).favorParameter(true).parameterName("mediaType")
                .ignoreAcceptHeader(true);//ignoreAcceptHeader设置true就不会根据request中accept来进行匹配httpmessageconverter
        //否则会造成浏览器直接输入接口，浏览器accept中没有application/json就会导致接口默认在浏览器中是xml格式返回
    }
}
