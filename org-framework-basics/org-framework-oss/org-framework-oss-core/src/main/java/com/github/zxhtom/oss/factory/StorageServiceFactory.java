package com.github.zxhtom.oss.factory;

import com.github.zxhtom.oss.exception.OssException;
import com.github.zxhtom.oss.model.FileStorageType;
import com.github.zxhtom.oss.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// StorageServiceFactory.java
@Component
public class StorageServiceFactory {

    @Autowired(required = false)
    private Map<String, StorageService> storageServiceMap;

    /**
     * 获取当前存储服务
     */
    public StorageService getCurrentStorageService() {
        // 根据配置的存储模式返回对应的服务
        for (StorageService service : storageServiceMap.values()) {
            // 实际项目中可以根据配置文件动态选择
            return service;
        }
        throw new OssException("未找到可用的存储服务");
    }

    /**
     * 根据类型获取存储服务
     */
    public StorageService getStorageService(FileStorageType storageType) {
        for (StorageService service : storageServiceMap.values()) {
            if (service.getStorageType() == storageType) {
                return service;
            }
        }
        throw new OssException("不支持的存储类型: " + storageType);
    }

    /**
     * 获取所有存储服务
     */
    public List<StorageService> getAllStorageServices() {
        return new ArrayList<>(storageServiceMap.values());
    }
}
