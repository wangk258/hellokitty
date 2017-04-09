package com.xiaoxuanfeng.plate.service.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONObject;
import com.xiaoxuanfeng.plate.common.constants.Constant;
import com.xiaoxuanfeng.plate.common.constants.MessageConstants;
import com.xiaoxuanfeng.plate.common.util.BeanPropertyUtil;
import com.xiaoxuanfeng.plate.service.bo.ServiceManageQueryObject;
import com.xiaoxuanfeng.plate.service.domain.ServiceManage;
import com.xiaoxuanfeng.plate.service.service.ServiceManageService;

import common.base.BaseController;
import common.bo.PageBean;
import common.bo.ResultFlag;
import common.util.FileUploadUtil;

@Controller
@RequestMapping(value="/service")
public class ServiceManageController  extends BaseController {

	@Autowired
	private ServiceManageService serviceManageService;
	
	@Autowired
	private FileUploadUtil fileService;
	/**
	 * 添加
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add.ajax",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject add(HttpServletRequest request,HttpSession session,ServiceManage serviceManage){
		
		JSONObject msgObj = new JSONObject();
		try {
			List<ServiceManage> serviceManages  = serviceManageService.list();
			if(serviceManages.size()>0){
				ServiceManage updateServiceManage  = serviceManages.get(0);
				BeanPropertyUtil.Copy(updateServiceManage, serviceManage, new String[] {"content"});
				serviceManageService.update(updateServiceManage);
			}else {
				serviceManageService.save(serviceManage);
			}
			msgObj.put("flag", true);
		} catch (Exception e) {
			msgObj.put("flag", false);
			e.printStackTrace();
		}
		return msgObj;
	}
	@RequestMapping(value = "/loadEdit", method = RequestMethod.GET)
	@ResponseBody
	public ServiceManage loadEdit(Long id) {
		try {
			
			if(id == null){
			 List<ServiceManage> serviceManages  = serviceManageService.list();	
			 if(serviceManages.size()>0){
				 serviceManages.get(0);
			 }
			}else {
				serviceManageService.get(id);
			}
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
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag delete(HttpServletRequest request,HttpSession session,@RequestParam("ids") String ids){
		try {
			if(StringUtils.isNotBlank(ids)){
				this.serviceManageService.delete(ids);
				this.setRightFlag(null);
			}
			else{
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
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag update(HttpServletRequest request,HttpSession session,ServiceManage serviceManage){
		try {
			if(serviceManage==null||null==serviceManage.getId()){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			this.serviceManageService.update(serviceManage);
			this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		
		return resultFlag;
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
	public ResultFlag list(HttpServletRequest request,HttpSession session,ServiceManageQueryObject serviceManageQueryObject){
		try {
			if(serviceManageQueryObject==null){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			PageBean<ServiceManage> pageBean=this.serviceManageService.list(serviceManageQueryObject);
			this.setRightFlag(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		
		return resultFlag;
	}
	
	
	@RequestMapping(value = "/uploadFile.ajax")
	public void uploadFile(MultipartRequest request, HttpServletResponse response) throws Exception {
		try {
			MultipartFile files = request.getFile("filedata");
			resultFlag = fileService.upload(request, files, Constant.UPLOADFILEDIR + "/service");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println(resultFlag.getData());
	}
}
