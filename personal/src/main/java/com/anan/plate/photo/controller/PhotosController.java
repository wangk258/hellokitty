package com.anan.plate.photo.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
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
import com.anan.plate.photo.bo.PhotoConstants;
import com.anan.plate.photo.bo.PhotosQueryObject;
import com.anan.plate.photo.domain.Album;
import com.anan.plate.photo.domain.Photos;
import com.anan.plate.photo.service.AlbumService;
import com.anan.plate.photo.service.PhotosService;

import common.base.BaseController;
import common.bo.PageBean;
import common.bo.ResultFlag;
import common.util.FileUploadUtil;

@Controller
@RequestMapping(value="/photos")
public class PhotosController extends BaseController {

	@Autowired
	private PhotosService photosService;
	@Autowired
	private AlbumService albumService;
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
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public void add(HttpServletResponse response,@RequestParam("imageUrls") String imageUrls,@RequestParam("albumId") Long albumId){
		try {
			if(StringUtils.isBlank(imageUrls)||albumId==null){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			String[] urlArray=imageUrls.split(",");
			if(urlArray.length>0){
				Album album=this.albumService.get(albumId);
				String s=urlArray[0];
				if(StringUtils.isBlank(album.getImageUrl())){
					album.setImageUrl(s);
					this.albumService.update(album);
					savePhoto(albumId, s,PhotoConstants.IS_COVER_YES);
				}
				else{
					savePhoto(albumId, s,PhotoConstants.IS_COVER_NO);
				}
				for (int i=1;i<urlArray.length;i++) {
					savePhoto(albumId, urlArray[i], PhotoConstants.IS_COVER_NO);
				}
				this.setRightFlag(null);
			}
			else{
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
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
	private void savePhoto(Long albumId, String s,Integer isCover) throws Exception {
		Photos photo=new Photos();
		photo.setAlbumId(albumId);
		photo.setImageUrl(s);
		photo.setIsCover(isCover);
		photo.setShowInTheMainPage(PhotoConstants.SHOW_IN_THE_MAINPAGE_NO);
		this.photosService.save(photo);
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
	public void delete(HttpServletRequest request,HttpServletResponse  response,@RequestParam("ids") String ids){
		try {
			if(StringUtils.isNotBlank(ids)){
				String[] idArray=ids.split(",");
				for (String id : idArray) {
					Photos photo=this.photosService.get(Long.valueOf(id));
					if(photo!=null){
						String imageUrl=photo.getImageUrl();
						String realPath=request.getSession().getServletContext().getRealPath(imageUrl);
						File file=new File(realPath);
						if(file.exists()){
							file.delete();
						}
					}
					this.photosService.delete(Long.valueOf(id));
				}
				this.setRightFlag(null);
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
	public ResultFlag update(HttpServletRequest request,HttpSession session,Photos photos){
		try {
			if(photos==null||null==photos.getId()){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			this.photosService.update(photos);
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
	public ModelAndView list(HttpServletRequest request,HttpSession session,PhotosQueryObject photosQueryObject){
		ModelAndView mv=new ModelAndView("bigpage/photo/list");
		try {
			if(StringUtils.isNotBlank(photosQueryObject.getPath())&&"admin".equals(photosQueryObject.getPath())){
				mv.setViewName("admin/photo/list");
			}
			photosQueryObject.setCurrentPage(-1);
			PageBean<Photos> pageBean=this.photosService.list(photosQueryObject);
			mv.addObject("photos", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error", e.getMessage());
		}
		return mv;
	}
	
	/**
	 * 设置相册封面
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/setCover",method = RequestMethod.POST)
	public void setCover(HttpServletResponse response,@RequestParam("id") Long id){
		try {
			if(id==null){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			Photos photo=this.photosService.get(id);
			if(photo==null){
				this.setErrorFlag(MessageConstants.DATA_NOT_EXISTS);
			}
			Album album=this.albumService.get(photo.getAlbumId());
			if(album==null){
				this.setErrorFlag(MessageConstants.DATA_NOT_EXISTS);
			}
			album.setImageUrl(photo.getImageUrl());
			this.albumService.update(album);
			this.setRightFlag(null);
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
	 * 设置照片到主页上显示
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/setToMainPage",method = RequestMethod.POST)
	public void setToMainPage(HttpServletResponse response,@RequestParam("id") Long id,@RequestParam("flag") Integer flag){
		try {
			if(id==null){
				 this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			Photos photo=this.photosService.get(id);
			if(photo==null){
				this.setErrorFlag(MessageConstants.DATA_NOT_EXISTS);
			}
			if(flag==1){
//				List<Photos> list=this.photosService.list("from Photos where showInTheMainPage=?",PhotoConstants.SHOW_IN_THE_MAINPAGE_YES);
//				if(list.size()>=5){
//					 this.setErrorFlag(MessageConstants.DATA_FULL);
//				}
//				photo.setShowInTheMainPage(PhotoConstants.SHOW_IN_THE_MAINPAGE_YES);
			}
			else{
				photo.setShowInTheMainPage(PhotoConstants.SHOW_IN_THE_MAINPAGE_NO);
			}
			this.photosService.update(photo);
			this.setRightFlag(null);
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
	
	@RequestMapping("/frontview")
	public ModelAndView frontView(){
		return new ModelAndView("bigpage/picture/picture");
	}
	
	@RequestMapping(value="/showinfront")
	public void showPhotoInMainPage(HttpServletResponse response){
		try {
//			List<Photos> photos=this.photosService.list("from Photos where showInTheMainPage=?", PhotoConstants.SHOW_IN_THE_MAINPAGE_YES);
//			response.getWriter().println(JSONArray.fromObject(photos));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/uploadPhoto")
	public void uploadPhoto(MultipartRequest req,HttpServletResponse response){
		try {
			MultipartFile file=req.getFile("filedata");
			this.fileService.upload(req, file,"photo");
			if(resultFlag.getError()){
				response.getWriter().print(resultFlag.getData());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
