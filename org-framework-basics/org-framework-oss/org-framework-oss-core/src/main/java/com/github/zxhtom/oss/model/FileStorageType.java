package com.github.zxhtom.oss.model;

// FileStorageType.java
public enum FileStorageType {
    LOCAL("local", "本地存储"),
    OSS("oss", "阿里云OSS"),
    MINIO("minio", "MinIO对象存储");

    private final String code;
    private final String desc;

    FileStorageType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static FileStorageType getByCode(String code) {
        for (FileStorageType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return LOCAL;
    }
}
