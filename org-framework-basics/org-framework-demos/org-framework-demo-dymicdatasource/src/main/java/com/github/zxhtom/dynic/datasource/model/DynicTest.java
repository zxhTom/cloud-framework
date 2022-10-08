package com.github.zxhtom.dynic.datasource.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * TODO
 *
 * @author zxhtom
 * 2022/9/27
 */
@TableName(value = "dynic_test")
@Data
public class DynicTest {
    private String id ;
    private String name;
}
