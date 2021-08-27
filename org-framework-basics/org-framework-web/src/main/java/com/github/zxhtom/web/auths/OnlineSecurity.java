package com.github.zxhtom.web.auths;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.web.auths
 * @date 2021/8/27 10:02
 * @description 用于获取当前系统登录用户 ， 若是放行接口获取信息null
 */
public interface OnlineSecurity {

    Object getOnlinePrincipal();

    String getOnlineUserName();

    String getInterfaceName();
}
