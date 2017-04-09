package com.xiaoxuanfeng.plate.common.constants;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

/**
 * 系统常量类
 * 
 * @author Administrator
 * 
 */
public class Constant {

	/**
	 * 项目绝对路径
	 * 
	 * @see 系统初始化设置
	 */
	public static String CTXDIR = "";
	
	public final static String SUPERLOGINID = "super";
	public final static String SUPERPW = "04dac8afe0ca501587bad66f6b5ce5ad";
	/**
	 * 系统上下文
	 * 
	 * @see 系统初始化设置
	 */
	public static String CTX = "";
	/**
	 * 
	 * 系统上下文环境
	 * 
	 * @see 不可修改
	 */
	public static WebApplicationContext context;
	
	public static ServletContext servletContext;
	
	/**
	 * 文件上传目录
	 */
	public final static String UPLOADFILEDIR =  "upload";

	// 每页几行
	public static final int PAGEPERROW = 10;

	// session时长
	public static final int MAX_SESSION_TIME = 20;
	/**
	 * 用户初始密码
	 */
	public static final String USERINIT_LOGINPWD = "123456";
	/**
	 * 管理员用户登录名
	 */
	public static final String ADMIN_LOGINID = "admin";

	public static final String LOGIN_FAIL = "LOGIN_FAIL";

	// Select选项 全部
	public static String SELECT_OPTION_ALL = "全部";
	// Select选项 请选择
	public static String SELECT_OPTION_CHOICE = "请选择";

	/**
	 * 向session中存入不同的登录对象
	 */
	public static class SessionLogin {
		/* session中存入普通用户 */
		public static final String SESSION_USER_LOGIN_KEY = "SESSION_LOGIN_KEY";
		/* session中存入管理员 */
		public static final String SESSION_MANAGER_LOGIN_KEY = "SESSION_MANAGER_LOGIN_KEY";
	}
	
}
