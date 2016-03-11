package com.xiaoxuanfeng.plate.common.util.filter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.xiaoxuanfeng.plate.common.constants.Constant;
import com.xiaoxuanfeng.plate.common.util.ServletContextCacheUtil;

/**
 * @desc <h3>系统初始化监听器,在系统启动时运行,进行一些初始化工作</h3>
 * @date 2014-8-7 上午10:54:43
 * @author tangk
 */
public class InitListener implements ServletContextListener {

	private final static Logger log = Logger.getLogger(InitListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {

		ServletContext ctx = arg0.getServletContext();
		Constant.servletContext = ctx;
		ctx.setAttribute("currentUser", Constant.SUPERLOGINID);
		/**
		 * 获取系统上下文环境
		 */
		Constant.context = (WebApplicationContext) ctx
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		// log.info("[获取系统上下文环境] "+Constant.context);
		// 设置
		Constant.CTXDIR = ctx.getRealPath("/");
		// log.info("[获取项目绝对路径] "+Constant.CTXDIR);
		// 设置系统上下文
		Constant.CTX = ctx.getContextPath();
		// log.info("[获取系统上下文] "+Constant.CTX);
		// log.info("系统初始化设置成功！");
		//
		// 初始化添加系统缓存变量
		try {
			ServletContextCacheUtil.initCache();
		} catch (Throwable e) {
			log.error(e);
		}
	}
}
