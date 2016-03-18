package com.anan.plate.diary.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.base.BaseController;
import org.bo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.anan.plate.constants.MessageConstants;
import com.anan.plate.diary.bo.DiaryQueryObject;
import com.anan.plate.diary.domain.Diary;
import com.anan.plate.diary.service.DiaryService;

@Controller
@RequestMapping(value="/diary")
public class DiaryController extends BaseController {

	@Autowired
	private DiaryService diaryService;
	
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
	public void add(HttpServletRequest request,HttpServletResponse response,HttpSession session,Diary diary) throws Exception{
		
		try {
			if(diary==null){
				resultFlag=this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			if(diary.getId()!=null){
				Diary old=this.diaryService.get(diary.getId());
				diary.setCreatTime(old.getCreatTime());
				this.diaryService.update(diary);
			}
			else{
				diary.setCreatTime(new Timestamp(new Date().getTime()));
				diaryService.save(diary);
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
	public void delete(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestParam("ids") String ids) throws Exception{
		try {
			if(StringUtils.isNotBlank(ids)){
				this.diaryService.delete(ids);
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
	public ModelAndView list(HttpServletRequest request,HttpSession session,DiaryQueryObject diaryQueryObject){
		ModelAndView mv=new ModelAndView("bigpage/diary/list");
		try {
			if(StringUtils.isNotBlank(diaryQueryObject.getPath())&&"admin".equals(diaryQueryObject.getPath())){
				mv.setViewName("admin/diary/list");
			}
			PageBean<Diary> pageBean=this.diaryService.list(diaryQueryObject);
			mv.addObject("pageBean", pageBean);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return mv.addObject("error",e.getMessage());
		}
	}
	
	/**
	 * 查询单条日记
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/getone")
	public ModelAndView getone(HttpServletRequest request,HttpSession session,Long id,String path){
		ModelAndView mv=new ModelAndView("bigpage/diary/view");
		try {
			if(StringUtils.isNotBlank(path)){
				if("admin".equals(path)){
					mv.setViewName("admin/diary/view");
				}
				else if("edit".equals(path)){
					mv.setViewName("admin/diary/add");
				}
				else if("add".equals(path)){
					mv.setViewName("admin/diary/add");
					return mv;
				}
			}
			if(id==null){
				mv.addObject("error",MessageConstants.DATA_TRANSFORM_ERROR);
			}
			else{
				Diary diary=this.diaryService.get(id);
				mv.addObject("diary",diary);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error",e.getMessage());
		}
		return mv;
	}
}
