package com.xiaoxuanfeng.plate.banner.controller;

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
import com.xiaoxuanfeng.plate.banner.bo.BannerQueryObject;
import com.xiaoxuanfeng.plate.banner.domain.Banner;
import com.xiaoxuanfeng.plate.banner.service.BannerService;
import com.xiaoxuanfeng.plate.common.constants.Constant;
import com.xiaoxuanfeng.plate.common.constants.MessageConstants;
import com.xiaoxuanfeng.plate.common.util.BeanPropertyUtil;
import com.xiaoxuanfeng.plate.common.util.RequestUtil;

import common.base.BaseController;
import common.bo.PageBean;
import common.util.FileUploadUtil;

@Controller
@RequestMapping(value="/banner")
public class BannerController  extends BaseController {

	@Autowired
	private BannerService bannerService;
	@Autowired
	private FileUploadUtil fileService;
	
	/**
	 * 添加
	 * @param request
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add.ajax",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject add(HttpServletRequest request,String[] imageUrls){
		JSONObject msgObj = new JSONObject();
		try {
			for (int i = 0; i < imageUrls.length; i++) {
				Banner banner = new Banner(imageUrls[i]);
				updateImageUrl(banner,request);
				bannerService.save(banner);
			}
			msgObj.put("flag", true);
		} catch (Exception e) {
			msgObj.put("flag", false);
			e.printStackTrace();
		}
		return msgObj;
	}
	/**
	 * 删除
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/remove.ajax")
	@ResponseBody
	public JSONObject remove(@RequestParam("ids") String ids){
		JSONObject msgObj = new JSONObject();
 		try {
			if(StringUtils.isNotBlank(ids)){
				this.bannerService.delete(ids);
				msgObj.put("flag", true);
			}
			else{
				msgObj.put("flag", false);
				msgObj.put("msg",MessageConstants.SELECT_ITEM_EMPTY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msgObj.put("flag", false);
		}
 		return msgObj;
	}
	
	/**
	 * 修改
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/edit.ajax",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject edit(HttpServletRequest request,Banner banner){
		JSONObject msgObj = new JSONObject();
 		try {
 			Banner updateBanner = bannerService.get(banner.getId());
 			updateImageUrl(banner, request);
 			BeanPropertyUtil.Copy(updateBanner, banner, new String[]{"imageUrl"});
				this.bannerService.update(updateBanner);
				msgObj.put("flag",true);
		} catch (Exception e) {
			e.printStackTrace();
			msgObj.put("flag",false);
		}
 		return msgObj;
	}
	/**
	 * 加载修改项
	 */
	@RequestMapping(value = "/loadEdit.ajax")
	@ResponseBody
	public Banner loadEdit(Long id){
		try {
			return bannerService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
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
	@RequestMapping(value = "/list.ajax",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject list(HttpServletRequest request,HttpSession session,BannerQueryObject bannerQueryObject){
		JSONObject msgObj = new JSONObject();
		try {
			if(bannerQueryObject==null){
				msgObj.put("msg", MessageConstants.DATA_TRANSFORM_ERROR);
			}
			PageBean<Banner> pageBean=this.bannerService.list(bannerQueryObject);
			msgObj.put("rows", pageBean.getRecordList());
			msgObj.put("total", pageBean.getRecordCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgObj;
	}
	@RequestMapping(value = "/uploadFile.ajax")
	public void uploadFile(MultipartRequest request, HttpServletResponse response) throws Exception {
		try {
			MultipartFile files = request.getFile("filedata");
			resultFlag = fileService.upload(request, files, Constant.UPLOADFILEDIR + "/banner");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println(resultFlag.getData());
	}
	@RequestMapping(value = "/loadCheckedCount.ajax", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadCheckedCount() {
		JSONObject msgObj = new JSONObject();
		try {
				 msgObj.put("total",bannerService.executeBySQL("select count(*) from Banner where showHomePage = 1 "));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgObj;
	}
	@RequestMapping(value = "/updateProductShowState.ajax", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject updateProductShowState(Long id,Integer state) {
		JSONObject msgObj = new JSONObject();
		try {
				 Banner product = bannerService.get(id);
				 if(product!=null){
					 product.setShowHomePage(state);
					 bannerService.update(product);
					 msgObj.put("flag", true);
				 }
		} catch (Exception e) {
			msgObj.put("flag", false);
			e.printStackTrace();
		}
		return msgObj;
	}
	private void updateImageUrl(Banner banner, HttpServletRequest request) throws Exception {
		if (StringUtils.isNotEmpty(banner.getImageUrl()))
			banner.setImageUrl(banner.getImageUrl().replace(RequestUtil.getBasePath(request), ""));
	}
}
