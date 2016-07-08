package com.xiaoxuanfeng.plate.process.controller;

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
import com.xiaoxuanfeng.plate.process.bo.ProcessQueryObject;
import com.xiaoxuanfeng.plate.process.domain.Process;
import com.xiaoxuanfeng.plate.process.service.ProcessService;
import common.bo.PageBean;
import common.bo.ResultFlag;
import common.rdbms.base.BaseController;
import common.util.FileUploadUtil;

@Controller
@RequestMapping(value="/process")
public class ProcessController  extends BaseController<Process> {

	@Autowired
	private ProcessService processService;
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
	public JSONObject add(HttpServletRequest request,Process process){
		
		JSONObject msgObj = new JSONObject();
		try {
			if(process != null && process.getId() != null){
				Process updateProcess  = processService.get(process.getId());
				BeanPropertyUtil.Copy(updateProcess, process, new String[] {"content"});
				processService.update(updateProcess);
			}else {
				processService.save(process);
			}
			msgObj.put("flag", true);
		} catch (Exception e) {
			msgObj.put("flag", false);
			e.printStackTrace();
		}
		return msgObj;
	}
	/**
	 * 加载修改项
	 */
	@RequestMapping(value = "/loadEdit.ajax")
	@ResponseBody
	public Process loadEdit(Long id){
		try {
			if(id != null){
				processService.get(id);
			}else{
				List<Process> processes  = processService.list();	
				 if(processes.size()>0){
					 processes.get(0);
				 }
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
				this.processService.delete(ids);
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
	public ResultFlag update(HttpServletRequest request,HttpSession session,Process process){
		try {
			if(process==null||null==process.getId()){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			this.processService.update(process);
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
	public ResultFlag list(HttpServletRequest request,HttpSession session,ProcessQueryObject processQueryObject){
		try {
			if(processQueryObject==null){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			PageBean<Process> pageBean=this.processService.list(processQueryObject);
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
			resultFlag = fileService.upload(request, files, Constant.UPLOADFILEDIR + "/process");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println(resultFlag.getData());
	}
}
