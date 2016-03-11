package com.xiaoxuanfeng.plate.user.service;

import javax.servlet.http.HttpServletRequest;

import org.base.BaseService;

import com.xiaoxuanfeng.plate.user.domain.User;

public interface UserService extends BaseService<User> {
	
	public String login(HttpServletRequest request,User user) throws Exception;

}
