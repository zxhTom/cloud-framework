package com.github.zxhtom.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.exception
 * @date 2021/8/31 11:10
 * @description 用于描述系统逻辑异常
 */
@Data
public class BusinessException extends RuntimeException{
    private Integer code=0;
    private String msg;

    public BusinessException(String msg) {
        this.msg = msg;
    }
}
