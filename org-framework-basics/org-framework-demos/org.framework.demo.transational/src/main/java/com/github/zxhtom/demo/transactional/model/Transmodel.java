package com.github.zxhtom.demo.transactional.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * TODO
 *
 * @author zxhtom
 * 2022/1/23
 */
@Data
@TableName("transation_test")
public class Transmodel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer age;
    private String name;
    private String code;
}
