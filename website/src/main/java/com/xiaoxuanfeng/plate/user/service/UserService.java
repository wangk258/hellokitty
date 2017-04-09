package com.xiaoxuanfeng.plate.user.service;

import javax.servlet.http.HttpServletRequest;

import com.xiaoxuanfeng.plate.user.domain.User;

import common.base.BaseService;

public interface UserService extends BaseService<User> {
	
	String login(HttpServletRequest request, User user) throws Exception;

}
