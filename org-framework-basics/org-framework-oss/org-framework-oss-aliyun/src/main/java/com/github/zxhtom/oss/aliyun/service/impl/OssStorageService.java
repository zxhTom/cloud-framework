package com.github.zxhtom.oss.aliyun.service.impl;

import com.aliyun.oss.OSS;
import com.github.zxhtom.oss.model.FileInfo;
import com.github.zxhtom.oss.model.FileStorageType;
import com.github.zxhtom.oss.service.FileValidateService;
import com.github.zxhtom.oss.service.ImageProcessService;
import com.github.zxhtom.oss.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

// OssStorageService.java
@Service
@Slf4j
@ConditionalOnExpression(
        "'${app.file.upload.storage.mode:}'.equals('oss')"
)
public class OssStorageService implements StorageService {

    @Value("${app.file.storage.oss.bucket-name}")
    private String bucketName;

    @Value("${app.file.storage.oss.domain}")
    private String domain;

    @Autowired
    private OSS ossClient;

    @Autowired
    private FileValidateService fileValidateService;

    @Autowired
    private ImageProcessService imageProcessService;

    @PostConstruct
    public void init() {
        // 检查Bucket是否存在，不存在则创建
        try {
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                log.info("创建OSS Bucket: {}", bucketName);
            }
        } catch (Exception e) {
            log.error("OSS Bucket初始化失败", e);
        }
    }

    @Override
    public FileStorageType getStorageType() {
        return FileStorageType.OSS;
    }

    @Override
    public FileInfo uploadFile(MultipartFile file, String directory) throws IOException {
        fileValidateService.validateFile(file);

        String originalFilename = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);
        String storageFilename = generateStorageFilename(fileExtension);
        String objectName = directory + "/" + storageFilename;

        // 上传到OSS
        ossClient.putObject(bucketName, objectName, file.getInputStream());

        return buildFileInfo(file, objectName, storageFilename);
    }

    @Override
    public FileInfo uploadImage(MultipartFile file, String directory) throws IOException {
        FileInfo fileInfo = uploadFile(file, directory);

        try {
            // 生成缩略图并上传
            if (fileInfo.getContentType().startsWith("image/")) {
                BufferedImage originalImage = ImageIO.read(file.getInputStream());
                BufferedImage thumbnail = imageProcessService.generateThumbnail(originalImage, 200, 200);

                String thumbObjectName = directory + "/thumb/thumb_" + fileInfo.getStorageFilename();
                uploadBufferedImage(thumbnail, thumbObjectName, "jpeg");

                fileInfo.setThumbPath(thumbObjectName);
                fileInfo.setThumbUrl(domain + "/" + thumbObjectName);
            }
        } catch (Exception e) {
            log.warn("生成缩略图失败: {}", e.getMessage());
        }

        return fileInfo;
    }

    @Override
    public String uploadAndGetUrl(MultipartFile file, String directory) throws IOException {
        FileInfo fileInfo = uploadFile(file, directory);
        return fileInfo.getFullUrl();
    }

    @Override
    public boolean deleteFile(String filePath) {
        try {
            ossClient.deleteObject(bucketName, filePath);

            // 同时删除缩略图
            String thumbPath = getThumbPath(filePath);
            if (fileExists(thumbPath)) {
                ossClient.deleteObject(bucketName, thumbPath);
            }

            return true;
        } catch (Exception e) {
            log.error("删除OSS文件失败: {}", filePath, e);
            return false;
        }
    }

    @Override
    public String getFileUrl(String filePath) {
        return domain + "/" + filePath;
    }

    @Override
    public boolean fileExists(String filePath) {
        try {
            return ossClient.doesObjectExist(bucketName, filePath);
        } catch (Exception e) {
            log.error("检查OSS文件存在失败: {}", filePath, e);
            return false;
        }
    }

    private void uploadBufferedImage(BufferedImage image, String objectName, String format) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, format, os);

        byte[] imageBytes = os.toByteArray();
        ByteArrayInputStream is = new ByteArrayInputStream(imageBytes);

        ossClient.putObject(bucketName, objectName, is);
    }

    private FileInfo buildFileInfo(MultipartFile file, String objectName, String storageFilename) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOriginalFilename(file.getOriginalFilename());
        fileInfo.setStorageFilename(storageFilename);
        fileInfo.setFilePath(objectName);
        fileInfo.setFullUrl(domain + "/" + objectName);
        fileInfo.setContentType(file.getContentType());
        fileInfo.setFileSize(file.getSize());
        fileInfo.setStorageType(FileStorageType.OSS.getCode());
        fileInfo.setUploadTime(new Date());

        return fileInfo;
    }

    private String generateStorageFilename(String extension) {
        return UUID.randomUUID().toString().replace("-", "") + "." + extension;
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    private String getThumbPath(String filePath) {
        String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
        String directory = filePath.substring(0, filePath.lastIndexOf("/"));
        return directory + "/thumb/thumb_" + filename;
    }
}
