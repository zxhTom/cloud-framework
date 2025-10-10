package com.github.zxhtom.login.core.dto;

import com.github.zxhtom.login.core.model.MiniUser;
import com.github.zxhtom.login.core.model.User;
import lombok.Data;

/**
 * TODO
 *
 * @author zxhtom
 * 10/9/25
 */
@Data
public class CombineUser {
    private User maltcloud;
    private MiniUser outUser;
}
