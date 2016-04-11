package com.xiaoxuanfeng.plate.msg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.xiaoxuanfeng.plate.msg.bo.MsgQueryObject;
import com.xiaoxuanfeng.plate.msg.domain.Msg;
import com.xiaoxuanfeng.plate.msg.service.MsgService;
import common.bo.PageBean;
import common.rdbms.base.BaseController;

@Controller
@RequestMapping(value="/msg")
public class MsgController  extends BaseController {

	@Autowired
	private MsgService msgService;
	
	/**
	 * 添加
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add.php",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject add(HttpServletRequest request,Msg msg){
		
		JSONObject msgObj = new JSONObject();
		try {
			if(msg != null ){
				msgService.save(msg);
			}
			msgObj.put("flag", true);
			msgObj.put("msg", "留言成功！");
		} catch (Exception e) {
			msgObj.put("flag", false);
			msgObj.put("msg", "留言失败！");
			e.printStackTrace();
		}
		return msgObj;
	}
	/**
	 * 加载修改项
	 */
	@RequestMapping(value = "/loadEdit.ajax")
	@ResponseBody
	public Msg loadEdit(Long id){
		try {
			return msgService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 删除
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/remove.ajax")
	@ResponseBody
	public JSONObject remove(@RequestParam("ids") String ids){
		JSONObject msgObj = new JSONObject();
		try {
			msgService.delete(ids);
			msgObj.put("flag",true);
		} catch (Exception e) {
			msgObj.put("flag",false);
			e.printStackTrace();
		}
		return msgObj;
	}
	
	
	
	/**
	 * 分页查询
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/list.ajax",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject list(HttpServletRequest request,HttpSession session,MsgQueryObject msgQueryObject){
		JSONObject msgObj = new JSONObject();
		PageBean<Msg> pageBean;
		try {
			pageBean = this.msgService
					.list(msgQueryObject);
			msgObj.put("rows", pageBean.getRecordList());
			msgObj.put("total", pageBean.getRecordCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgObj;
	}
	
	@RequestMapping(value = "/msg.php")
	public ModelAndView list(){
		return new ModelAndView("front/msg");
	}
}
