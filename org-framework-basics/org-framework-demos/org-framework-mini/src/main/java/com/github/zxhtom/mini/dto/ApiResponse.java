package com.github.zxhtom.mini.dto;

import lombok.Data;

/**
 * TODO
 *
 * @author zxhtom
 * 10/3/25
 */

// ApiResponse.java
@Data
public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;

    public static ApiResponse success(Object data) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage("成功");
        response.setData(data);
        return response;
    }

    public static ApiResponse error(String message) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }
}
