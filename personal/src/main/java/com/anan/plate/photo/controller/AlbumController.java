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
@RequestMapping(value="/photo/album")
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
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag  add(HttpServletResponse response,HttpSession session,Album album){
		try {
			if(album==null){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			if(StringUtils.isEmpty(album.getImageUrl())){
				album.setImageUrl(PhotoConstants.ALBUM_DEFAULT);
			}
			albumService.save(album);
			this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getCause().getMessage());
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
				List<Photos> list=(List<Photos>) this.photoService.list("from Photos where albumId=" + id);
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
	 * 分页查询
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.POST)
	@ResponseBody
	public ResultFlag list(HttpServletRequest request,HttpSession session,AlbumQueryObject albumQueryObject){
		try {
			PageBean<Album> pageBean=this.albumService.list(albumQueryObject);
			this.setRightFlag(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getCause().getMessage());
		}
		return resultFlag;
	}
}
