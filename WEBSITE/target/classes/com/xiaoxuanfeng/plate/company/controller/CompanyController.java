package com.xiaoxuanfeng.plate.company.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.base.BaseController;
import org.bo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.util.FileUploadUtil;

import com.alibaba.fastjson.JSONObject;
import com.xiaoxuanfeng.plate.common.constants.Constant;
import com.xiaoxuanfeng.plate.common.constants.MessageConstants;
import com.xiaoxuanfeng.plate.common.util.BeanPropertyUtil;
import com.xiaoxuanfeng.plate.company.bo.CompanyQueryObject;
import com.xiaoxuanfeng.plate.company.domain.Company;
import com.xiaoxuanfeng.plate.company.service.CompanyService;

@Controller
@RequestMapping(value = "/company")
public class CompanyController extends BaseController {

	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;

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
	public JSONObject add(HttpServletRequest request, Company company) {
		JSONObject msgObj = new JSONObject();
		try {
			companyService.save(company);
			msgObj.put("flag", true);
		} catch (Exception e) {
			msgObj.put("flag", false);
			e.printStackTrace();
		}
		return msgObj;
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
	@RequestMapping(value = "/remove.ajax")
	@ResponseBody
	public JSONObject remove(@RequestParam("ids") String ids) {
		JSONObject msg = new JSONObject();
		try {
			if (StringUtils.isNotBlank(ids)) {
				this.companyService.delete(ids);
				msg.put("flag", true);
			} else {
				msg.put("flag", false);
				msg.put("msg", MessageConstants.SELECT_ITEM_EMPTY);
			}
		} catch (Exception e) {
			msg.put("flag", false);
			e.printStackTrace();
		}
		return msg;
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
	@RequestMapping(value = "/loadEdit.ajax")
	@ResponseBody
	public Company loadEdit(Long id) {
		try {
			return companyService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/edit.ajax", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject edit(HttpServletRequest request, Company company) {
		JSONObject msgObj = new JSONObject();
		try {
			Company updateCompany = companyService.get(company.getId());
			BeanPropertyUtil.Copy(updateCompany, company, new String[] {
					 "content", "imageUrl" });
			companyService.update(updateCompany);

			msgObj.put("flag", true);
		} catch (Exception e) {
			msgObj.put("flag", false);
			e.printStackTrace();
		}
		return msgObj;
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
	@RequestMapping(value = "/list.ajax", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject list(HttpServletRequest request,
			CompanyQueryObject companyQueryObject) {
		JSONObject msgObj = new JSONObject();
		try {
			PageBean<Company> pageBean = this.companyService
					.list(companyQueryObject);
			msgObj.put("rows", pageBean.getRecordList());
			msgObj.put("total", pageBean.getRecordCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgObj;
	}

	@RequestMapping(value = "/uploadFile.ajax")
	public void uploadFile(MultipartRequest request,
			HttpServletResponse response) throws Exception {
		try {
			MultipartFile files = request.getFile("filedata");
			resultFlag = fileService.upload(request, files,
					Constant.UPLOADFILEDIR + "/company");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println(resultFlag.getData());
	}
}
