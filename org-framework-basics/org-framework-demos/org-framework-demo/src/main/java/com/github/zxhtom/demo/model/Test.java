package com.github.zxhtom.demo.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.zxhtom.demo.enums.SexEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.model
 * @date 2021/8/25 15:31
 */
@Data
@TableName("test")
public class Test extends TestParent {
    @TableId(type=IdType.AUTO)
    private Integer id;
    private String name;
    private SexEnum sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthDate;

}
