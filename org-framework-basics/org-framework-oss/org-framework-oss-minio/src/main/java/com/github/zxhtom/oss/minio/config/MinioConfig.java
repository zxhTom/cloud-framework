package com.github.zxhtom.oss.minio.config;

import com.github.zxhtom.oss.minio.model.FileMinio;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// MinioConfig.java
@Configuration
@ConditionalOnExpression(
        "'${app.file.upload.storage.mode:}'.equals('minio')"
)
public class MinioConfig {

    @Autowired
    FileMinio fileMinio;
    @Bean
    public MinioClient minioClient() {
        String endpoint = fileMinio.getEndpoint();
        String accessKey = fileMinio.getAccessKey();
        String secretKey = fileMinio.getSecretKey();
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
