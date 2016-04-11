package com.xiaoxuanfeng.plate.user.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoxuanfeng.plate.common.constants.Constant;
import com.xiaoxuanfeng.plate.common.util.MD5Util;
import com.xiaoxuanfeng.plate.common.util.SessionUtil;
import com.xiaoxuanfeng.plate.user.dao.UserDao;
import com.xiaoxuanfeng.plate.user.domain.User;
import com.xiaoxuanfeng.plate.user.service.UserService;
import common.rdbms.base.BaseDao;
import common.rdbms.base.BaseServiceImpl;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public BaseDao<User> getDao() {
		return userDao;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public String login(HttpServletRequest request,User user) throws Exception {
		if (user != null && StringUtils.isNotEmpty(user.getUsername()) && StringUtils.isNotEmpty(user.getPassword())) {
			try {
				User loginUser = null;
				// super登录
				if (Constant.SUPERLOGINID.equals(user.getUsername())) {
					if(Constant.SUPERPW.equals(MD5Util.string2MD5(user.getPassword()))){
						loginUser = new User(user.getUsername(), user.getPassword());
					}
				} else {
					loginUser = userDao.get(" from User where 1=1 and username = ? and password = ?",
							user.getUsername(), MD5Util.string2MD5(user.getPassword()));
				}
				if (loginUser != null) {
					SessionUtil.getInstance().setUserToSession(request, loginUser);
					return "redirect:/admin/pages/index.jsp";
				}
				else{
					request.setAttribute("loginMsg", "您输入的帐号或密码有误,");
					request.setAttribute("username", user.getUsername());
					return "/admin/pages/login.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
