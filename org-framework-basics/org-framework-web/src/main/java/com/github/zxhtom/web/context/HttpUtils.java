package com.github.zxhtom.web.context;

import com.alibaba.fastjson.JSONObject;
import com.github.zxhtom.web.model.HttpResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HTTP工具类
 * @author Louis
 * @date Oct 29, 2018
 */
public class HttpUtils {

	/**
	 * 获取HttpServletRequest对象
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	 * 输出信息到浏览器
	 * @param response
	 * @throws IOException
	 */
	public static void write(HttpServletResponse response, Object data) throws IOException {
		response.setContentType("application/json; charset=utf-8");
        HttpResult result = HttpResult.ok(data);
        String json = JSONObject.toJSONString(result);
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
	}
	
}
