package com.github.zxhtom.dynic.datasource.aop;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "database_list")
public class DatabaseList {
    private String factoryCode;
    private String user;
    private String url;
    private String password;
}
