package com.github.zxhtom.flowable.model;

import lombok.Data;

/**
 * TODO
 *
 * @author zxhtom
 * 2023/9/12
 */
@Data
public class FlowableIdmAppUserModel {
    private String email;
    private String firstName;
    private String fullName;
    private String groups;
    private String id;
    private String lastName;
    private String privileges;
    private String tenantId;
}
