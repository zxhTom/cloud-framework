package com.github.zxhtom.oss.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "app.file.upload")
@Data
public class FileApp {
    private Long maxSize;
    private List<String> allowedTypes;
    private String storage;
}
