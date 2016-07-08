package com.xiaoxuanfeng.plate.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoxuanfeng.plate.common.constants.MessageConstants;
import com.xiaoxuanfeng.plate.user.bo.UserQueryObject;
import com.xiaoxuanfeng.plate.user.domain.User;
import com.xiaoxuanfeng.plate.user.service.UserService;
import common.bo.PageBean;
import common.bo.ResultFlag;
import common.rdbms.base.BaseController;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 添加
	 * 
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/login.ajax", method = RequestMethod.POST)
	public String login(HttpServletRequest request, User user) throws Exception {
		System.out.println(user.getUsername()+","+user.getPassword());
		return userService.login(request, user);
	}

	@RequestMapping(value = "/loginOut.ajax")
	public String loginOut(HttpServletRequest request, User user) {

		return "redirect:/admin/pages/login.jsp";
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag add(HttpServletRequest request, HttpSession session, User user) {
		try {
			if (user == null) {
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			userService.save(user);
			this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		return resultFlag;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag delete(HttpServletRequest request, HttpSession session, @RequestParam("ids") String ids) {
		try {
			if (StringUtils.isNotBlank(ids)) {
				this.userService.delete(ids);
				this.setRightFlag(null);
			} else {
				this.setErrorFlag(MessageConstants.SELECT_ITEM_EMPTY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		return resultFlag;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag update(HttpServletRequest request, HttpSession session, User user) {
		try {
			if (user == null || null == user.getId()) {
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			this.userService.update(user);
			this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		return resultFlag;
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag list(HttpServletRequest request, HttpSession session, UserQueryObject userQueryObject) {
		try {
			if (userQueryObject == null) {
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			PageBean<User> pageBean = this.userService.list(userQueryObject);
			this.setRightFlag(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		
		return resultFlag;
	}
}
