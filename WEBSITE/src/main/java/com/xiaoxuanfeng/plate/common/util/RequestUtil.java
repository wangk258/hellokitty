package com.xiaoxuanfeng.plate.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * <h2>文件名称：RequestUtil.java</h2></br> <h3>文件描述：</h3></br> <h3>详细说明：</h3></br>
 * 
 * @author tangkun
 * @date 2014-10-3 下午1:23:54
 * @version 1.0
 */
public final class RequestUtil {

	public final static String getBasePath(HttpServletRequest request) throws Exception {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		return basePath;
	}
}
