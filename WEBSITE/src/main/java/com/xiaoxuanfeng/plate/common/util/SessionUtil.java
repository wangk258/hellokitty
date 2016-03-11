package com.xiaoxuanfeng.plate.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xiaoxuanfeng.plate.common.constants.Constant;
import com.xiaoxuanfeng.plate.user.domain.User;


public class SessionUtil {
	private static SessionUtil sessionUtil;

	private SessionUtil() {
	}

	/**
	 * 获取session中写入的用户
	 * 
	 * @param request
	 * @return 用户Bean实例
	 */
	public User getSessionInUser(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		Object ob = httpSession
				.getAttribute(Constant.SessionLogin.SESSION_USER_LOGIN_KEY);
		if (null != ob)
			return (User) ob;
		return null;
	}

	public User getSessionInManager(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		Object ob = httpSession
				.getAttribute(Constant.SessionLogin.SESSION_MANAGER_LOGIN_KEY);
		if (null != ob)
			return (User) ob;
		return null;
	}

	/**
	 * 往session中写入当前用户
	 * 
	 * @param request
	 * @param userBean
	 *            当前用户Bean
	 */
	public void setUserToSession(HttpServletRequest request, User userBean) {
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute(Constant.SessionLogin.SESSION_USER_LOGIN_KEY,
				userBean);
	}

	public void setManageToSession(HttpServletRequest request, User userBean) {
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute(
				Constant.SessionLogin.SESSION_MANAGER_LOGIN_KEY, userBean);
	}

	/**
	 * 移除当前session中的用户
	 * 
	 * @param request
	 */
	public void remove(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession
				.removeAttribute(Constant.SessionLogin.SESSION_USER_LOGIN_KEY);
	}

	public static SessionUtil getInstance() {
		if (sessionUtil == null)
			sessionUtil = new SessionUtil();
		return sessionUtil;
	}

	/**
	 * 
	 * @desc 获取请求ip地址
	 * @param request
	 * @return
	 * @throws Exception
	 * @exception 异常说明
	 * @date 2014-9-25 下午05:16:37
	 * @author tangk
	 */
	public static final String getRemoteHost(javax.servlet.http.HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
}
