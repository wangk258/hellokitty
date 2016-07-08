package com.xiaoxuanfeng.plate.millCycloneHome.controller;

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
import com.xiaoxuanfeng.plate.millCycloneHome.bo.MillCycloneHomeQueryObject;
import com.xiaoxuanfeng.plate.millCycloneHome.domain.MillCycloneHome;
import com.xiaoxuanfeng.plate.millCycloneHome.service.MillCycloneHomeService;
import common.bo.PageBean;
import common.bo.ResultFlag;
import common.rdbms.base.BaseController;
import common.util.FileUploadUtil;

@Controller
@RequestMapping(value = "/millCycloneHome")
public class MillCycloneHomeController extends BaseController<MillCycloneHome> {

	@Autowired
	private MillCycloneHomeService millCycloneHomeService;

	@Autowired
	private FileUploadUtil fileService;
	/**
	 * 添加
	 * 
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add.ajax", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject add(HttpServletRequest request, MillCycloneHome millCycloneHome) {

		JSONObject msgObj = new JSONObject();
		try {
			if (millCycloneHome != null && millCycloneHome.getId() != null) {
				MillCycloneHome updateMillCycloneHome = millCycloneHomeService.get(millCycloneHome.getId());
				BeanPropertyUtil.Copy(updateMillCycloneHome, millCycloneHome, new String[] { "content" });
				millCycloneHomeService.update(updateMillCycloneHome);
			} else {
				millCycloneHomeService.save(millCycloneHome);
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
	public MillCycloneHome loadEdit(Long id) {
		try {
			if (id != null) {
				millCycloneHomeService.get(id);
			} else {
				List<MillCycloneHome> millCycloneHomes = millCycloneHomeService.list();
				if (millCycloneHomes.size() > 0)
					millCycloneHomes.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag delete(HttpServletRequest request, HttpSession session, @RequestParam("ids") String ids) {
		try {
			if (StringUtils.isNotBlank(ids)) {
				this.millCycloneHomeService.delete(ids);
				this.setRightFlag(null);
			} else {
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
	 * 
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag update(HttpServletRequest request, HttpSession session, MillCycloneHome millCycloneHome) {
		try {
			if (millCycloneHome == null || null == millCycloneHome.getId()) {
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			this.millCycloneHomeService.update(millCycloneHome);
			this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorFlag(e.getMessage());
		}
		return null;
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
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag list(HttpServletRequest request, HttpSession session,
			MillCycloneHomeQueryObject millCycloneHomeQueryObject) {
		try {
			if (millCycloneHomeQueryObject == null) {
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			PageBean<MillCycloneHome> pageBean = this.millCycloneHomeService.list(millCycloneHomeQueryObject);
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
			resultFlag = fileService.upload(request, files, Constant.UPLOADFILEDIR + "/millCycloneHome");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println(resultFlag.getData());
	}
}
