package com.github.zxhtom.oss.controller;

import com.github.zxhtom.oss.exception.OssException;
import com.github.zxhtom.oss.factory.StorageServiceFactory;
import com.github.zxhtom.oss.model.FileInfo;
import com.github.zxhtom.oss.service.StorageService;
import com.github.zxhtom.result.unity.ActionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

// FileUploadController.java
@RestController
@RequestMapping("/api/file")
@Slf4j
public class FileUploadController {

    @Autowired
    private StorageServiceFactory storageServiceFactory;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public ActionResult<FileInfo> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "directory", defaultValue = "common") String directory) {

        try {
            StorageService storageService = storageServiceFactory.getCurrentStorageService();
            FileInfo fileInfo = storageService.uploadFile(file, directory);

            log.info("文件上传成功: {} -> {}", file.getOriginalFilename(), fileInfo.getFilePath());

            return ActionResult.success(fileInfo);

        } catch (OssException e) {
            throw  e;
        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new OssException("文件上传失败:" + e.getMessage());
        }
    }

    /**
     * 上传图片（生成缩略图）
     */
    @PostMapping("/upload/image")
    public ActionResult<FileInfo> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "directory", defaultValue = "images") String directory) {

        try {
            StorageService storageService = storageServiceFactory.getCurrentStorageService();
            FileInfo fileInfo = storageService.uploadImage(file, directory);

            log.info("图片上传成功: {} -> {}", file.getOriginalFilename(), fileInfo.getFilePath());

            return ActionResult.success(fileInfo);

        } catch (OssException e) {
            return ActionResult.error(e.getMessage());
        } catch (Exception e) {
            log.error("图片上传失败", e);
            return ActionResult.error("图片上传失败");
        }
    }

    /**
     * 删除文件
     */
    @DeleteMapping("/delete")
    public ActionResult<Boolean> deleteFile(@RequestParam("filePath") String filePath) {
        try {
            StorageService storageService = storageServiceFactory.getCurrentStorageService();
            boolean deleted = storageService.deleteFile(filePath);

            if (deleted) {
                log.info("文件删除成功: {}", filePath);
                return ActionResult.success(true);
            } else {
                return ActionResult.error("文件删除失败");
            }

        } catch (Exception e) {
            log.error("文件删除失败: {}", filePath, e);
            return ActionResult.error("文件删除失败");
        }
    }

    /**
     * 批量上传文件
     */
    @PostMapping("/upload/batch")
    public ActionResult<List<FileInfo>> uploadFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam(value = "directory", defaultValue = "common") String directory) {

        try {
            StorageService storageService = storageServiceFactory.getCurrentStorageService();
            List<FileInfo> fileInfos = new ArrayList<>();

            for (MultipartFile file : files) {
                try {
                    FileInfo fileInfo = storageService.uploadFile(file, directory);
                    fileInfos.add(fileInfo);
                } catch (Exception e) {
                    log.warn("文件上传失败: {}", file.getOriginalFilename(), e);
                    // 继续处理其他文件
                }
            }

            return ActionResult.success(fileInfos);

        } catch (Exception e) {
            log.error("批量文件上传失败", e);
            return ActionResult.error("批量文件上传失败");
        }
    }
}
