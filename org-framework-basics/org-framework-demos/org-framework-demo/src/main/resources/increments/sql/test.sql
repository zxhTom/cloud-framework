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
