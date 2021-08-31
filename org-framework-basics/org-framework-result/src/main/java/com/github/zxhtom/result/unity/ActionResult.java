/**
 * 
 */
package com.github.zxhtom.result.unity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;


/**
 * 后端服务返回统一格式对象, 序列化成json字符串返回
 * { "code": 0, "message": null, "data": null }
 * code: 状态码, 0成功, 1一般业务异常, -1其他异常, -2非法访问
 * message: 错误消息
 * data: 数据
 * @author zxhtom
 */
@JsonInclude(Include.ALWAYS)
@Data
public class ActionResult {
	/**
	 * code: 状态码, 0成功, 1一般业务异常, -1其他异常, -2非法访问
	 */
	private Integer code;

	/**
	* 消息id
	*/
	private String instanceId;

	/**
	 * message: 错误消息
	 */
	private String message;

	/**
	 * data: 数据
	 */
	private Object data;

	public ActionResult(Integer code, String message, Object data) {
		this.code=code;
		this.message=message;
		this.data=data;
	}

	public ActionResult(Integer code,String instanceId, String message, Object data) {
		this.instanceId = instanceId;
		this.code=code;
		this.message=message;
		this.data=data;
	}

	
}
