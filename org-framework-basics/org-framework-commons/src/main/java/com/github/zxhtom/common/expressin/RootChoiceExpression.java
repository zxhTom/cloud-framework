package com.github.zxhtom.common.expressin;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.expressin
 * @date 2021/10/13 14:42
 */
public interface RootChoiceExpression {
    public boolean haslogined();
    public boolean hasRole(String role);
    public boolean hasAnyRole(String... roles);
}
