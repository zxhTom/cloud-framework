CREATE TABLE maltcloud.test (
    id BIGINT NOT NULL,
    name varchar(100) NULL,
    sex VARCHAR(255) NULL,
    birth_date DATE NULL,
    status_enum VARCHAR(255) NULL,
    create_time DATE null,
    update_time DATE null,
    delete_flag INTEGER null,
    owner VARCHAR(255) null,
    modifer VARCHAR(255) null,
    interface_entry VARCHAR(255) null,
    version INTEGER null,
    PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

CREATE TABLE maltcloud.mini_user (
     id BIGINT auto_increment NOT NULL COMMENT '主键ID',
     app_id varchar(100) NOT NULL COMMENT '第三方系统ID',
     open_id varchar(100) NOT NULL COMMENT '第三方系统用户唯一标识ID',
     user_id varchar(100) NULL COMMENT 'maltcloud系统预留用户ID',
     is_finished tinyint(1) DEFAULT NULL COMMENT '是否完善信息',
     CONSTRAINT MINI_USER_PK PRIMARY KEY (id),
     CONSTRAINT MINI_USER_UNIQUE UNIQUE KEY (app_id),
     CONSTRAINT MINI_USER_UNIQUE_1 UNIQUE KEY (open_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;
