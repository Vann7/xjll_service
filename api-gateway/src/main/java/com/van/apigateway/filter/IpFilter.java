package com.van.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Check ip blacklist.
 * 
 * @author Michael Liao
 */
@Component
public class IpFilter extends ZuulFilter {

	final Log log = LogFactory.getLog(getClass());

	/**
	 * 过滤器类型，决定过滤器在请求的那个生命周期中执行，
	 * 这里定义为pre，代表会在请求被路由之前执行
	 * routing: 在路由请求时被调用
	 * post:在routing和error过滤器之后被调用
	 * error:处理请求时发生错误时被调用
	 * @return
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 判断该过滤器是否需要被执行。
	 * @return
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 过滤器的具体逻辑
	 * @return
	 */
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s: %s", request.getMethod(), request.getRequestURI()));
		return null;
	}

	/**
	 * 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。
	 * @return
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

}
