package com.github.zxhtom.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.model
 * @date 2021/8/25 15:31
 */
@Data
@TableName("test")
public class Test {
    private String name;
}
