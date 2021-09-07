package com.qj.others.model;

import com.baomidou.mybatisplus.annotation.*;
import com.qj.others.enums.SexEnum;
import com.qj.others.model.TestParent;
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
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date birthDate;

}
