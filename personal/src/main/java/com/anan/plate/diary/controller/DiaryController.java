package com.anan.plate.diary.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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

import common.bo.PageBean;
import common.rdbms.base.BaseController;

@Controller
@RequestMapping(value = "/diary")
public class DiaryController extends BaseController<Diary> {

	@Autowired
	private DiaryService diaryService;

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
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public void add(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Diary diary){

		try {
			if (diary == null) {
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			else{
				if (diary.getId() != null) {
					Diary old = this.diaryService.get(diary.getId());
					diary.setCreatTime(old.getCreatTime());
					this.diaryService.update(diary);
				} else {
					diary.setCreatTime(new Timestamp(new Date().getTime()));
					diaryService.save(diary);
				}
				this.setRightFlag(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		try {
			this.writeObject(response,resultFlag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("ids") String ids) throws Exception {
		try {
			String result = this.diaryService.deleteDiaries(ids, Diary.class);
			this.setRightFlag(result);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		this.writeObject(response,resultFlag);
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
	@RequestMapping(value = "/list")
	public ModelAndView list(HttpSession session,
			DiaryQueryObject diaryQueryObject) {
		ModelAndView mv = new ModelAndView("bigpage/diary/list");
		try {
			if (StringUtils.isNotBlank(diaryQueryObject.getPath())
					&& "admin".equals(diaryQueryObject.getPath())) {
				mv.setViewName("admin/diary/list");
			}
			PageBean<Diary> pageBean = this.diaryService.list(diaryQueryObject);
			mv.addObject("pageBean", pageBean);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return mv.addObject("error", e.getMessage());
		}
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
	@RequestMapping(value = "/diaries")
	public void listwidthjson(HttpServletResponse response,
			DiaryQueryObject diaryQueryObject) {
		try {
			pageBean = this.diaryService.list(diaryQueryObject);
			this.setRightFlag(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		try {
			this.writeObject(response,resultFlag);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 查询单条日记
	 * 
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/single")
	public void getone(HttpServletRequest request,HttpServletResponse response,Long id) {
		try {
			if (id == null) {
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			} else {
				Diary diary = this.diaryService.get(id);
				this.setRightFlag(diary);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		try {
			this.writeObject(response, resultFlag);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
