/**
 * 
 */
package com.github.zxhtom.result.unity;

/**
 * 后端服务返回状态码
 * @author Tony
 */
public enum ActionResultCode {
	/**
	 * 服务调用发生业务异常BusinessException
	 */
	BUSINESS_ERROR(1),
	/**
	 * 服务调用成功
	 */
	SUCCESS(200),
	/**
	 * 服务调用发生除业务异常BusinessException外的其他异常
	 */
	OTHER_ERROR(-1),
	/**
	 * 无效令牌, 非法访问
	 */
	INVALID_TOKEN(-2),

	/**
	 * 重复提交
	 */
	DUPLICATE_SUBMIT(-3),
	
	/**
	 * 验证登录失败
	 */
	INVALID_LOGIN_TOKEN(401);

	private final Integer value;

	private ActionResultCode(Integer value) {
		this.value = value;
	}

	public final Integer getValue() {
		return value;
	}
}
