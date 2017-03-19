package com.anan.plate.message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anan.plate.message.bo.MessageQueryObject;
import com.anan.plate.message.domain.Message;
import com.anan.plate.message.service.MessageService;
import common.bo.PageBean;
import common.bo.ResultFlag;

@Controller
@RequestMapping(value="/message",method=RequestMethod.POST)
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	/**
	 * 添加
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag add(HttpServletRequest request,HttpSession session,Message message){
		try {
//			if(message==null||StringUtils.isBlank(message.getName())){
//				return new ResultFlag(false,"未能正确接收数据！");
//			}
			messageService.save(message);
			return new ResultFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultFlag(false,e.getMessage());
		}
	}
	/**
	 * 删除
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag delete(HttpServletRequest request,HttpSession session,@RequestParam("ids") String ids){
		try {
			if(StringUtils.isNotBlank(ids)){
				this.messageService.delete(ids);
				return new ResultFlag(true);
			}
			else{
				return new ResultFlag(false,"请勾选删除的项目!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultFlag(false,e.getMessage());
		}
	}
	
	/**
	 * 修改
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag update(HttpServletRequest request,HttpSession session,Message message){
		try {
			if(message==null||null==message.getId()){
				return new ResultFlag(false,"数据传输错误！");
			}
			this.messageService.update(message);
			return new ResultFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultFlag(false,e.getMessage());
		}
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag list(HttpServletRequest request,HttpSession session,MessageQueryObject messageQueryObject){
		try {
			if(messageQueryObject==null){
				return new ResultFlag(false,"数据传输错误！");
			}
			PageBean<Message> pageBean=this.messageService.list(messageQueryObject);
			ResultFlag resultFlag=new ResultFlag();
			resultFlag.setError(true);
			resultFlag.setData(pageBean);
			return resultFlag;
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultFlag(false,e.getMessage());
		}
	}
}
