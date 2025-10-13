package com.github.zxhtom.oss.service;

import com.github.zxhtom.oss.exception.OssException;
import com.github.zxhtom.oss.model.FileApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

// FileValidateService.java
@Service
@Slf4j
public class FileValidateService {

/*
    @Value("${app.file.upload.max-size:10485760}")
    private long maxFileSize;

    @Value("${app.file.upload.allowed-types:[]}")
    private List<String> allowedTypes;
*/
    @Autowired
    FileApp fileApp;

    /**
     * 验证文件
     */
    public void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new OssException("文件不能为空");
        }
        long maxFileSize = fileApp.getMaxSize();
        List<String> allowedTypes = fileApp.getAllowedTypes();
        // 检查文件大小
        if (file.getSize() > maxFileSize) {
            throw new OssException("文件大小不能超过 " + (maxFileSize / 1024 / 1024) + "MB");
        }

        // 检查文件类型
        String contentType = file.getContentType();
        if (!allowedTypes.contains(contentType)) {
            throw new OssException("不支持的文件类型: " + contentType);
        }

        // 检查文件扩展名
        String filename = file.getOriginalFilename();
        if (!isValidFileExtension(filename)) {
            throw new OssException("文件扩展名不合法");
        }

        // 安全检查
        if (!isSafeFile(file)) {
            throw new OssException("文件安全检查未通过");
        }
    }

    private boolean isValidFileExtension(String filename) {
        if (filename == null) {
            return false;
        }

        String extension = filename.substring(filename.lastIndexOf(".") + 1)
                .toLowerCase();
        Set<String> allowedExtensions = Set.of("jpg", "jpeg", "png", "gif", "webp", "pdf");
        return allowedExtensions.contains(extension);
    }

    private boolean isSafeFile(MultipartFile file) {
        try {
            // 简单的文件头验证
            byte[] header = new byte[12];
            try (InputStream is = file.getInputStream()) {
                int read = is.read(header);
                if (read < 12) {
                    return false;
                }
            }

            // 检查常见文件格式的文件头
            return isJpeg(header) || isPng(header) || isGif(header) || isWebp(header) || isPdf(header);

        } catch (IOException e) {
            return false;
        }
    }

    private boolean isJpeg(byte[] header) {
        return header[0] == (byte) 0xFF && header[1] == (byte) 0xD8;
    }

    private boolean isPng(byte[] header) {
        return header[0] == (byte) 0x89 && header[1] == (byte) 0x50 &&
                header[2] == (byte) 0x4E && header[3] == (byte) 0x47;
    }

    private boolean isGif(byte[] header) {
        return header[0] == (byte) 'G' && header[1] == (byte) 'I' &&
                header[2] == (byte) 'F';
    }

    private boolean isWebp(byte[] header) {
        return header[0] == (byte) 'R' && header[1] == (byte) 'I' &&
                header[2] == (byte) 'F' && header[3] == (byte) 'F';
    }

    private boolean isPdf(byte[] header) {
        return header[0] == (byte) '%' && header[1] == (byte) 'P' &&
                header[2] == (byte) 'D' && header[3] == (byte) 'F';
    }
}
