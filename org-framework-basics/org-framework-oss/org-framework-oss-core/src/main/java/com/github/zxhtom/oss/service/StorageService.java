package com.github.zxhtom.oss.service;

import com.github.zxhtom.oss.model.FileInfo;
import com.github.zxhtom.oss.model.FileStorageType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// StorageService.java
public interface StorageService {

    /**
     * 获取存储类型
     */
    FileStorageType getStorageType();

    /**
     * 上传文件
     */
    FileInfo uploadFile(MultipartFile file, String directory) throws IOException;

    /**
     * 上传图片（支持生成缩略图）
     */
    FileInfo uploadImage(MultipartFile file, String directory) throws IOException;

    /**
     * 上传文件并返回访问URL
     */
    String uploadAndGetUrl(MultipartFile file, String directory) throws IOException;

    /**
     * 删除文件
     */
    boolean deleteFile(String filePath);

    /**
     * 获取文件访问URL
     */
    String getFileUrl(String filePath);

    /**
     * 文件是否存在
     */
    boolean fileExists(String filePath);
}
