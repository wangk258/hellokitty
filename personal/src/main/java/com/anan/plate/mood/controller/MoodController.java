package com.anan.plate.mood.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.anan.plate.constants.MessageConstants;
import com.anan.plate.mood.bo.MoodConstant;
import com.anan.plate.mood.bo.MoodQueryObject;
import com.anan.plate.mood.domain.Mood;
import com.anan.plate.mood.domain.MoodBackground;
import com.anan.plate.mood.service.MoodBackgroundService;
import com.anan.plate.mood.service.MoodService;

import common.base.BaseController;
import common.bo.ResultFlag;
import common.util.FileUploadUtil;

@Controller
@RequestMapping(value="/mood")
public class MoodController  extends BaseController {

	@Autowired
	private MoodService moodService;
	@Autowired
	private MoodBackgroundService moodBackgroundService;
	
	@Autowired
	private FileUploadUtil fileService;
	
	@RequestMapping("/addview")
	public ModelAndView addView() throws Exception{
		ModelAndView mv=new ModelAndView("admin/mood/add");
//		List<MoodBackground> list=this.moodBackgroundService.list("from MoodBackground");
//		mv.addObject("images",list);
		return mv;
	}
	
	/**
	 * 添加
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public ResultFlag add(HttpServletRequest request,HttpSession session,Mood mood){
		try {
			if(mood==null){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			moodService.save(mood);
			this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
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
				this.moodService.delete(ids);
				this.setRightFlag(null);
			}
			else{
				this.setErrorFlag(MessageConstants.SELECT_ITEM_EMPTY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		return null;
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
	public ResultFlag update(HttpServletRequest request,HttpSession session,Mood mood){
		try {
			if(mood==null||null==mood.getId()){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			this.moodService.update(mood);
			this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 载入所有背景图片
	 */
	@RequestMapping(value = "/loadbackground")
	public ModelAndView loadbackground(HttpServletResponse response,MoodQueryObject moodQueryObject){
		ModelAndView mv=new ModelAndView();
		try {
			if(StringUtils.isNotBlank(moodQueryObject.getPath())&&"admin".equals(moodQueryObject.getPath())){
				mv.setViewName("admin/mood/background");
			}
//			List<MoodBackground> images=this.moodBackgroundService.list("from MoodBackground");
//			mv.addObject("images",images);
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error",e.getMessage());
		}
		return mv;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request,HttpSession session,MoodQueryObject moodQueryObject){
		ModelAndView mv=new ModelAndView("bigpage/mood/list");
		try {
			if(StringUtils.isNotBlank(moodQueryObject.getPath())&&"admin".equals(moodQueryObject.getPath())){
				mv.setViewName("admin/mood/list");
			}
//			List<Mood> list=this.moodService.list("from Mood");
//		    mv.addObject("moods",list);
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error",e.getMessage());
		}
		return mv;
	}
	
	@RequestMapping("/uploadbackground")
	public void uploadPhoto(MultipartRequest req,HttpServletResponse response){
		try {
			MultipartFile file=req.getFile("filedata");
			ResultFlag resultFlag=this.fileService.upload(req, file, MoodConstant.BACKGROUND_UPLOADPATH);
			if(resultFlag.getError()){
				response.getWriter().print(resultFlag.getData());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
