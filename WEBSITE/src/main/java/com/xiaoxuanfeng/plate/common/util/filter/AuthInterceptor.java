package com.xiaoxuanfeng.plate.common.util.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.xiaoxuanfeng.plate.common.util.SessionUtil;
import com.xiaoxuanfeng.plate.user.domain.User;

/**
 * @desc <h3>用户请求拦截器</h3>
 * @date 2014-9-1 下午03:52:30
 * @author tangk
 */
public class AuthInterceptor implements Filter{

	private List<String> excludeUrls = new ArrayList<String>();
	public void init(FilterConfig filterConfig) throws ServletException {
		excludeUrls = Arrays.asList(filterConfig.getInitParameter("excludeUrls").split(","));
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String url =	httpServletRequest.getRequestURI();
		User user =SessionUtil.getInstance().getSessionInUser(httpServletRequest);
		if(user != null){
			chain.doFilter(request, response);
		}else {
			if(excludeUrls.contains(url)){
				chain.doFilter(request, response);
			}else {
				httpServletRequest.getRequestDispatcher("/admin/pages/login.jsp").forward(request, response);
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	

}
