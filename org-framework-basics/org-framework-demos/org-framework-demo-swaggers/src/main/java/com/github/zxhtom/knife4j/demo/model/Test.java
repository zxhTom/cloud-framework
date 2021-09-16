package com.github.zxhtom.knife4j.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.model
 * @date 2021/8/25 15:31
 */
@Data
@ApiModel("测试用例")
public class Test {
    @ApiModelProperty(value = "id",required = true,example = "1")
    private Integer id;
    @ApiModelProperty(value = "name",required = false,example = "zxhtom")
    private String name;
    @ApiModelProperty(value = "birthDate",required = false,example = "2021-09-03")
    private Date birthDate;

}
