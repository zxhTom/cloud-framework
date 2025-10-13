package com.github.zxhtom.oss.local.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.file.upload.storage.local")
@Data
public class FileLocal {
    private String basePath="/data/uploads";
    private String baseUrl="http://localhost:8080/uploads";
    private String accessPrefix;
}
