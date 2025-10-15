/**
 * 
 */
package com.github.zxhtom.result.unity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.UUID;


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
@ToString
public class ActionResult<T> {
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

	public static <T> ActionResult success(T data) {
		return status(HttpStatus.OK).data(data).build();
	}
	public static ActionResult error(Exception exception) {
		return status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).stack(exception.getMessage()).build();
	}
	public static ActionResult error(String exception) {
		return status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception).build();
	}

	public static <T> ApiResponseBuilder<T> status(HttpStatus httpStatus) {
		return (ApiResponseBuilder<T>) ActionResult.builder().httpStatus(httpStatus.value());
	}
	public static <T> ApiResponseBuilder<T> builder() {
		return new ApiResponseBuilder();
	}

	/**
	 * sub
	 */

	public static class ApiResponseBuilder<T> {

		private Integer httpStatus;
		private String message;
		private String messageCode;
		private String stack;
		private T data;

		ApiResponseBuilder() {
		}

		public ApiResponseBuilder<T> httpStatus(Integer httpStatus) {
			this.httpStatus = httpStatus;
			return this;
		}

		public ApiResponseBuilder<T> message(String message) {
			this.message = message;
			return this;
		}

		public ApiResponseBuilder<T> messageCode(String messageCode) {
			this.messageCode = messageCode;
			return this;
		}
		public ApiResponseBuilder<T> stack(String stack) {
			this.stack = stack;
			return this;
		}
		public ApiResponseBuilder<T> data(T data) {
			this.data = data;
			return this;
		}

		public ActionResult build() {
			return new ActionResult(this.httpStatus, UUID.randomUUID().toString(),this.message, this.data);
		}


		@Override
		public String toString() {
			return "ResponseMsg.ApiResponseBuilder(httpStatus=" + this.httpStatus + ", message=" + this.message + ", data=" + this.data + ")";
		}
	}
}
