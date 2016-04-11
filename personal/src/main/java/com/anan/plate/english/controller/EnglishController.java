package com.anan.plate.english.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.anan.plate.constants.MessageConstants;
import com.anan.plate.english.bo.EnglishQueryObject;
import com.anan.plate.english.domain.English;
import com.anan.plate.english.service.EnglishService;
import common.bo.PageBean;
import common.bo.ResultFlag;
import common.rdbms.base.BaseController;
import common.util.FileUploadUtil;

@Controller
@RequestMapping(value="/english")
public class EnglishController  extends BaseController {

	@Autowired
	private EnglishService englishService;
	@Autowired
	private FileUploadUtil fileService;
	
	/**
	 * 添加
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public void add(HttpServletResponse response,English english) throws Exception{
		try {
			if(english==null){
				resultFlag=this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			if(null==english.getId()){
				englishService.save(english);
			}
			else{
				English old=this.englishService.get(english.getId());
				if(old==null){
					resultFlag=this.setErrorFlag(MessageConstants.DATA_NOT_EXISTS);
				}
				english.setMp3Url(old.getMp3Url());
				english.setOrginalName(old.getOrginalName());
				englishService.update(english);
			}
			resultFlag=this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			resultFlag=this.setErrorFlag(e.getMessage());
		}
		this.write(response);
	}
	/**
	 * 删除
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public void delete(HttpServletResponse response ,@RequestParam("ids") String ids) throws Exception{
		try {
			if(StringUtils.isNotBlank(ids)){
				this.englishService.delete(ids);
				resultFlag=this.setRightFlag(null);
			}
			else{
				resultFlag=this.setErrorFlag(MessageConstants.SELECT_ITEM_EMPTY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultFlag= this.setErrorFlag(e.getMessage());
		}
		this.write(response);
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(EnglishQueryObject query){
		ModelAndView mv=new ModelAndView("bigpage/english/list");
		try {
			if(StringUtils.isNotBlank(query.getPath()) && "admin".equals(query.getPath())){
				mv.setViewName("admin/english/list");
			}
			PageBean<English> pageBean=this.englishService.list(query);
			mv.addObject("pageBean",pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error",e.getMessage());
		}
		return  mv;
	}
	
	/**
	 * 查询单个实体(后台)
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/getOne")
	public ModelAndView getOne(Long id){
		ModelAndView mv=new ModelAndView("admin/english/add");
		try {
			if(id==null){
				return mv;
			}
			English english=this.englishService.get(id);
			mv.addObject(english);
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error",e.getMessage());
		}
		return mv;
	}
	/**
	 * 查询单个实体
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/one")
	public void one(HttpServletResponse response,Long id) throws Exception{
		try {
			English english=this.englishService.get(id);
			resultFlag=this.setRightFlag(english);
		} catch (Exception e) {
			e.printStackTrace();
			resultFlag=this.setErrorFlag(e.getMessage());
		}
		this.write(response);
	}
	
	/**
	 * mp3文件上传
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/mp3Upload",method = RequestMethod.POST)
	@ResponseBody
	public void mp3Upload(MultipartRequest request,HttpServletResponse response){
		try {
			MultipartFile files=request.getFile("filedata");
			if(files!=null){
				ResultFlag flag=this.fileService.upload(request,files,"english_player");
				if(flag.getFlag()){
					JSONObject jo=JSONObject.fromObject(flag.getData());
					PrintWriter pw=response.getWriter();
					pw.println(jo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
