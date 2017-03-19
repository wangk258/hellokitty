package com.anan.plate.photo.controller;

import java.io.IOException;
import java.io.OutputStream;
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
import org.springframework.web.servlet.ModelAndView;

import com.anan.plate.constants.MessageConstants;
import com.anan.plate.photo.bo.AlbumQueryObject;
import com.anan.plate.photo.bo.PhotoConstants;
import com.anan.plate.photo.domain.Album;
import com.anan.plate.photo.domain.Photos;
import com.anan.plate.photo.service.AlbumService;
import com.anan.plate.photo.service.PhotosService;

import common.base.BaseController;
import common.bo.PageBean;
import common.bo.ResultFlag;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/album")
public class AlbumController extends BaseController<Album> {

	@Autowired
	private AlbumService albumService;
	@Autowired
	private PhotosService photoService;
	
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
	public ResultFlag  add(HttpServletResponse response,HttpSession session,Album album){
		try {
			if(album==null){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			album.setImageUrl(PhotoConstants.ALBUM_DEFAULT);
			albumService.save(album);
			this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		return resultFlag;
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
	public void delete(HttpServletResponse response,@RequestParam("id") Long id){
		try {
			if(id!=null){
				List<Photos> list=this.photoService.list("from Photos where albumId=?",id);
				if(list.size()>0){
					 this.setErrorFlag(MessageConstants.SUB_ITEM_EXISTS);
				}
				else{
					this.albumService.delete(id);
					 this.setRightFlag(null);
				}
			}
			else{
				 this.setErrorFlag(MessageConstants.SELECT_ITEM_EMPTY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			 this.setErrorFlag(e.getMessage());
		}
		try {
			OutputStream out=response.getOutputStream();
			out.write(JSONObject.fromObject(resultFlag).toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
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
	public ResultFlag update(HttpServletRequest request,HttpSession session,Album album){
		try {
			if(album==null||null==album.getId()){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			this.albumService.update(album);
			this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		return null;
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
	public ModelAndView list(HttpServletRequest request,HttpSession session,AlbumQueryObject albumQueryObject){
		ModelAndView mv=new ModelAndView("bigpage/photo/list");
		try {
			if(StringUtils.isNotBlank(albumQueryObject.getPath())&&"admin".equals(albumQueryObject.getPath())){
				mv.setViewName("admin/photo/list");
			}
			PageBean<Album> pageBean=this.albumService.list(albumQueryObject);
			mv.addObject("pageBean",pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error",e.getMessage());
		}
		return mv;
	}
}
