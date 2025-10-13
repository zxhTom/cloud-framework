package com.github.zxhtom.oss.aliyun.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// OssConfig.java
@Configuration
@ConditionalOnProperty(name = "app.file.storage.mode", havingValue = "oss")
public class OssConfig {

    @Value("${app.file.storage.oss.endpoint}")
    private String endpoint;

    @Value("${app.file.storage.oss.access-key-id}")
    private String accessKeyId;

    @Value("${app.file.storage.oss.access-key-secret}")
    private String accessKeySecret;

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
