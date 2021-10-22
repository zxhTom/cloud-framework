package com.github.zxhtom.login.core.dto;

import com.github.zxhtom.login.core.model.Menu;
import lombok.Data;

import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.core.dto
 * @date 2021/10/12 16:35
 */
@Data
public class MenuDto extends Menu {

    private Integer type;

    private List<MenuDto> menuList;

}
