package com.github.zxhtom.oss.minio.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.file.upload.storage.minio")
@Data
public class FileMinio {

    private String endpoint="http://localhost:9001";

    private String accessKey="adminhello";

    private String secretKey="1231231239";

    private String bucketName="maltcloud";

    private String domain="http://localhost:9000";
}
