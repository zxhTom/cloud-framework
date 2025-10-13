package com.github.zxhtom.minio.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// MinioConfig.java
@Configuration
@ConditionalOnProperty(name = "app.file.storage.mode", havingValue = "minio")
public class MinioConfig {

    @Value("${app.file.storage.minio.endpoint}")
    private String endpoint;

    @Value("${app.file.storage.minio.access-key}")
    private String accessKey;

    @Value("${app.file.storage.minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
