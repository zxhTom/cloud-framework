package com.github.zxhtom.oss.model;

import lombok.Data;

import java.util.Date;

// FileInfo.java
@Data
public class FileInfo {
    private String originalFilename;
    private String storageFilename;
    private String filePath;
    private String fullUrl;
    private String contentType;
    private Long fileSize;
    private String storageType;
    private String md5;
    private Date uploadTime;

    // 图片特有属性
    private Integer width;
    private Integer height;

    // 缩略图信息
    private String thumbPath;
    private String thumbUrl;
}
