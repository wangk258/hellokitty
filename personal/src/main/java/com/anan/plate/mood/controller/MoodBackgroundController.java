package com.anan.plate.mood.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.base.BaseController;
import org.bo.PageBean;
import org.bo.ResultFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anan.plate.constants.MessageConstants;
import com.anan.plate.mood.bo.MoodBackgroundQueryObject;
import com.anan.plate.mood.domain.MoodBackground;
import com.anan.plate.mood.service.MoodBackgroundService;

@Controller
@RequestMapping(value="/moodBackground",method=RequestMethod.POST)
public class MoodBackgroundController  extends BaseController {

	@Autowired
	private MoodBackgroundService moodBackgroundService;
	
	/**
	 * 添加
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add")
	public void add(HttpServletResponse response,@RequestParam("imageUrls") String imageUrls){
		ResultFlag resultFlag=null;
		try {
			for(String imageUrl : imageUrls.split(",")){
				MoodBackground mb=new MoodBackground();
				mb.setImageUrl(imageUrl);
				moodBackgroundService.save(mb);
			}
			resultFlag=this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			resultFlag=this.setErrorFlag(e.getMessage());
		}
		try {
			OutputStream out=response.getOutputStream();
			out.write(JSONObject.fromObject(resultFlag).toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
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
				this.moodBackgroundService.delete(ids);
				return this.setRightFlag(null);
			}
			else{
				return this.setErrorFlag(MessageConstants.SELECT_ITEM_EMPTY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.setErrorFlag(e.getMessage());
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
	public ResultFlag update(HttpServletRequest request,HttpSession session,MoodBackground moodBackground){
		try {
			if(moodBackground==null||null==moodBackground.getId()){
				return this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			this.moodBackgroundService.update(moodBackground);
			return this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setErrorFlag(e.getMessage());
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
	public ResultFlag list(HttpServletRequest request,HttpSession session,MoodBackgroundQueryObject moodBackgroundQueryObject){
		try {
			if(moodBackgroundQueryObject==null){
				return this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			PageBean<MoodBackground> pageBean=this.moodBackgroundService.list(moodBackgroundQueryObject);
			return this.setRightFlag(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setErrorFlag(e.getMessage());
		}
	}
}
