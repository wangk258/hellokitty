package com.anan.plate.appraise.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.bo.PageBean;
import org.bo.ResultFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anan.plate.appraise.bo.AppraiseQueryObject;
import com.anan.plate.appraise.domain.Appraise;
import com.anan.plate.appraise.service.AppraiseService;

@Controller
@RequestMapping(value="/appraise",method=RequestMethod.POST)
public class AppraiseController {

	@Autowired
	private AppraiseService appraiseService;
	
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
	public ResultFlag add(HttpServletRequest request,HttpSession session,Appraise appraise){
		try {
//			if(appraise==null||StringUtils.isBlank(appraise.getName())){
//				return new ResultFlag(false,"未能正确接收数据！");
//			}
			appraise.setCreateTime(new Timestamp(new Date().getTime()));
			appraiseService.save(appraise);
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
				this.appraiseService.delete(ids);
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
	public ResultFlag update(HttpServletRequest request,HttpSession session,Appraise appraise){
		try {
			if(appraise==null||null==appraise.getId()){
				return new ResultFlag(false,"数据传输错误！");
			}
			this.appraiseService.update(appraise);
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
	public ResultFlag list(HttpServletRequest request,HttpSession session,AppraiseQueryObject appraiseQueryObject){
		try {
			if(appraiseQueryObject==null){
				return new ResultFlag(false,"数据传输错误！");
			}
			PageBean<Appraise> pageBean=this.appraiseService.list(appraiseQueryObject);
			ResultFlag resultFlag=new ResultFlag();
			resultFlag.setFlag(true);
			resultFlag.setData(pageBean);
			return resultFlag;
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultFlag(false,e.getMessage());
		}
	}
}
