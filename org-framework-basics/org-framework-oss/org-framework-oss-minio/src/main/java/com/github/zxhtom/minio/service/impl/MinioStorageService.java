package com.github.zxhtom.minio.service.impl;

import com.github.zxhtom.oss.exception.OssException;
import com.github.zxhtom.oss.model.FileInfo;
import com.github.zxhtom.oss.model.FileStorageType;
import com.github.zxhtom.oss.service.FileValidateService;
import com.github.zxhtom.oss.service.ImageProcessService;
import com.github.zxhtom.oss.service.StorageService;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

// MinioStorageService.java
@Service
@Slf4j
@ConditionalOnProperty(name = "app.file.storage.mode", havingValue = "minio")
public class MinioStorageService implements StorageService {

    @Value("${app.file.storage.minio.bucket-name:uploads}")
    private String bucketName;

    @Value("${app.file.storage.minio.domain:http://localhost:9000}")
    private String domain;

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private FileValidateService fileValidateService;

    @Autowired
    private ImageProcessService imageProcessService;

    @PostConstruct
    public void init() {
        // 检查Bucket是否存在，不存在则创建
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                log.info("创建MinIO Bucket: {}", bucketName);
            }
        } catch (Exception e) {
            log.error("MinIO Bucket初始化失败", e);
        }
    }

    @Override
    public FileStorageType getStorageType() {
        return FileStorageType.MINIO;
    }

    @Override
    public FileInfo uploadFile(MultipartFile file, String directory) throws IOException {
        fileValidateService.validateFile(file);

        String originalFilename = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);
        String storageFilename = generateStorageFilename(fileExtension);
        String objectName = directory + "/" + storageFilename;

        // 上传到MinIO
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        } catch (Exception e) {
            throw new OssException(e.getMessage());
        }

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
                fileInfo.setThumbUrl(domain + "/" + bucketName + "/" + thumbObjectName);
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
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filePath)
                            .build()
            );

            // 同时删除缩略图
            String thumbPath = getThumbPath(filePath);
            if (fileExists(thumbPath)) {
                minioClient.removeObject(
                        RemoveObjectArgs.builder()
                                .bucket(bucketName)
                                .object(thumbPath)
                                .build()
                );
            }

            return true;
        } catch (Exception e) {
            log.error("删除MinIO文件失败: {}", filePath, e);
            return false;
        }
    }

    @Override
    public String getFileUrl(String filePath) {
        return domain + "/" + bucketName + "/" + filePath;
    }

    @Override
    public boolean fileExists(String filePath) {
        try {
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filePath)
                            .build()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void uploadBufferedImage(BufferedImage image, String objectName, String format) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, format, os);

        byte[] imageBytes = os.toByteArray();
        ByteArrayInputStream is = new ByteArrayInputStream(imageBytes);

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(is, imageBytes.length, -1)
                        .contentType("image/jpeg")
                        .build()
        );
    }

    private FileInfo buildFileInfo(MultipartFile file, String objectName, String storageFilename) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOriginalFilename(file.getOriginalFilename());
        fileInfo.setStorageFilename(storageFilename);
        fileInfo.setFilePath(objectName);
        fileInfo.setFullUrl(domain + "/" + bucketName + "/" + objectName);
        fileInfo.setContentType(file.getContentType());
        fileInfo.setFileSize(file.getSize());
        fileInfo.setStorageType(FileStorageType.MINIO.getCode());
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
