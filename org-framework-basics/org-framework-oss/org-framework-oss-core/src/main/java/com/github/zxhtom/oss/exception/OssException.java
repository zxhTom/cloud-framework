package com.github.zxhtom.oss.exception;

import lombok.Data;

@Data
public class OssException extends RuntimeException{

    private Integer code=0;
    private String msg;

    public OssException(String msg) {
        this.msg = msg;
    }
    public OssException(Integer code , String msg) {
        this.code = code;
        this.msg = msg;
    }
}
