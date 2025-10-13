package com.github.zxhtom.oss.local.service.impl;

import com.github.zxhtom.oss.local.model.FileLocal;
import com.github.zxhtom.oss.model.FileInfo;
import com.github.zxhtom.oss.model.FileStorageType;
import com.github.zxhtom.oss.service.FileValidateService;
import com.github.zxhtom.oss.service.ImageProcessService;
import com.github.zxhtom.oss.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

// LocalStorageService.java
@Service
@Slf4j
//@ConditionalOnProperty(name = "app.file.storage.mode", havingValue = "local")
@ConditionalOnExpression(
        "'${app.file.storage.mode:}'.equals('local') or " +
                "'${app.file.storage.mode:}'.isEmpty()"  // 支持默认值
)
public class LocalStorageService implements StorageService {


    @Autowired
    FileLocal fileLocal;
    @Autowired
    private FileValidateService fileValidateService;

    @Autowired
    private ImageProcessService imageProcessService;

    @PostConstruct
    public void init() {
        // 创建基础目录
        File baseDir = new File(fileLocal.getBasePath());
        if (!baseDir.exists()) {
            boolean created = baseDir.mkdirs();
            if (created) {
                log.info("创建本地存储目录: {}", baseDir.getAbsolutePath());
                return;
            }
        }
        log.info("本地存储目录: {}", baseDir.getAbsolutePath());
    }

    @Override
    public FileStorageType getStorageType() {
        return FileStorageType.LOCAL;
    }

    @Override
    public FileInfo uploadFile(MultipartFile file, String directory) throws IOException {
       log.debug("=== 文件上传诊断 ===");
       log.debug("原始文件名: " + file.getOriginalFilename());
       log.debug("文件大小: " + file.getSize() + " bytes");
       log.debug("ContentType: " + file.getContentType());

        // 验证文件
        fileValidateService.validateFile(file);

        // 生成存储文件名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);
        String storageFilename = generateStorageFilename(fileExtension);
        String relativePath = directory + "/" + storageFilename;
        String fullPath = fileLocal.getBasePath() + "/" + relativePath;

       log.debug("目标路径: " + fullPath);

        // 创建目录
        File destFile = new File(fullPath);
        File parentDir = destFile.getParentFile();
       log.debug("父目录: " + parentDir.getAbsolutePath());
       log.debug("父目录是否存在: " + parentDir.exists());

        if (!parentDir.exists()) {
            boolean created = parentDir.mkdirs();
           log.debug("创建目录结果: " + created);
           log.debug("创建后目录是否存在: " + parentDir.exists());
        }

        // 检查临时文件状态
        checkMultipartFileState(file);

        // 保存文件 - 使用安全的方式
        safeTransferFile(file, destFile);

        // 构建文件信息
        return buildFileInfo(file, relativePath, storageFilename);
    }

    /**
     * 检查MultipartFile状态
     */
    private void checkMultipartFileState(MultipartFile file) {
        try {
           log.debug("=== MultipartFile状态检查 ===");

            // 检查文件是否为空
           log.debug("isEmpty: " + file.isEmpty());
           log.debug("Size: " + file.getSize());

            // 尝试读取文件内容
            try (InputStream is = file.getInputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead = is.read(buffer);
               log.debug("可读取字节数: " + bytesRead);

                if (bytesRead == -1) {
                   log.debug("❌ 文件流已结束 - 临时文件可能已被清理");
                } else {
                   log.debug("✅ 文件流正常");
                }
            }

        } catch (Exception e) {
            System.err.println("文件状态检查失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 安全的文件传输方法
     */
    private void safeTransferFile(MultipartFile file, File destFile) throws IOException {
       log.debug("开始传输文件...");

        try {
            // 方法1: 直接传输
            file.transferTo(destFile);
           log.debug("✅ transferTo成功");

        } catch (IOException e) {
            System.err.println("❌ transferTo失败: " + e.getMessage());

            // 方法2: 使用InputStream复制
           log.debug("尝试使用InputStream复制...");
            try (InputStream is = file.getInputStream();
                 FileOutputStream fos = new FileOutputStream(destFile)) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
               log.debug("✅ InputStream复制成功");

            } catch (IOException e2) {
                System.err.println("❌ InputStream复制也失败: " + e2.getMessage());

                // 方法3: 使用NIO
               log.debug("尝试使用NIO复制...");
                try (InputStream is = file.getInputStream()) {
                    java.nio.file.Files.copy(
                            is,
                            destFile.toPath(),
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING
                    );
                   log.debug("✅ NIO复制成功");
                } catch (IOException e3) {
                    System.err.println("❌ 所有方法都失败: " + e3.getMessage());
                    throw new IOException("文件保存失败: " + e3.getMessage(), e3);
                }
            }
        }

        // 验证文件是否真的保存成功
        if (destFile.exists()) {
           log.debug("✅ 文件保存验证: 存在, 大小: " + destFile.length() + " bytes");
        } else {
            System.err.println("❌ 文件保存验证: 文件不存在！");
        }
    }

    @Override
    public FileInfo uploadImage(MultipartFile file, String directory) throws IOException {
        FileInfo fileInfo = uploadFile(file, directory);

        try {
            // 如果是图片，生成缩略图
            if (fileInfo.getContentType().startsWith("image/")) {
                String thumbPath = generateThumbnail(new File(fileLocal.getBasePath() + "/" + fileInfo.getFilePath()), directory);
                fileInfo.setThumbPath(thumbPath);
                fileInfo.setThumbUrl(fileLocal.getBaseUrl() + "/" + thumbPath);
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
            File file = new File(fileLocal.getBasePath() + "/" + filePath);
            boolean deleted = file.delete();

            // 同时删除缩略图
            String thumbPath = getThumbPath(filePath);
            File thumbFile = new File(fileLocal.getBasePath() + "/" + thumbPath);
            if (thumbFile.exists()) {
                thumbFile.delete();
            }

            return deleted;
        } catch (Exception e) {
            log.error("删除文件失败: {}", filePath, e);
            return false;
        }
    }

    @Override
    public String getFileUrl(String filePath) {
        return fileLocal.getBaseUrl() + "/" + filePath;
    }

    @Override
    public boolean fileExists(String filePath) {
        File file = new File(fileLocal.getBasePath() + "/" + filePath);
        return file.exists();
    }

    private String generateStorageFilename(String extension) {
        return UUID.randomUUID().toString().replace("-", "") + "." + extension;
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    private FileInfo buildFileInfo(MultipartFile file, String relativePath, String storageFilename) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOriginalFilename(file.getOriginalFilename());
        fileInfo.setStorageFilename(storageFilename);
        fileInfo.setFilePath(relativePath);
        fileInfo.setFullUrl(fileLocal.getBaseUrl() + "/" + relativePath);
        fileInfo.setContentType(file.getContentType());
        fileInfo.setFileSize(file.getSize());
        fileInfo.setStorageType(FileStorageType.LOCAL.getCode());
        fileInfo.setUploadTime(new Date());

        // 如果是图片，获取尺寸信息
        if (file.getContentType().startsWith("image/")) {
            try {
                BufferedImage image = ImageIO.read(file.getInputStream());
                if (image != null) {
                    fileInfo.setWidth(image.getWidth());
                    fileInfo.setHeight(image.getHeight());
                }
            } catch (IOException e) {
                log.warn("获取图片尺寸失败: {}", e.getMessage());
            }
        }

        return fileInfo;
    }

    private String generateThumbnail(File originalFile, String directory) throws IOException {
        BufferedImage originalImage = ImageIO.read(originalFile);
        BufferedImage thumbnail = imageProcessService.generateThumbnail(originalImage, 200, 200);

        String thumbFilename = "thumb_" + originalFile.getName();
        String thumbRelativePath = directory + "/thumb/" + thumbFilename;
        String thumbFullPath = fileLocal.getBasePath() + "/" + thumbRelativePath;

        File thumbFile = new File(thumbFullPath);
        thumbFile.getParentFile().mkdirs();

        ImageIO.write(thumbnail, "jpeg", thumbFile);

        return thumbRelativePath;
    }

    private String getThumbPath(String filePath) {
        String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
        String directory = filePath.substring(0, filePath.lastIndexOf("/"));
        return directory + "/thumb/thumb_" + filename;
    }
}
