package com.xiaoxuanfeng.plate.productType.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xiaoxuanfeng.plate.common.util.BeanPropertyUtil;
import com.xiaoxuanfeng.plate.productType.bo.ProductTypeQueryObject;
import com.xiaoxuanfeng.plate.productType.domain.ProductType;
import com.xiaoxuanfeng.plate.productType.service.ProductTypeService;

import common.base.BaseController;
import common.bo.PageBean;

@Controller
@RequestMapping(value = "/productType")
public class ProductTypeController extends BaseController {

	private final static Logger LOGGER = Logger.getLogger(ProductTypeController.class);
	@Autowired
	private ProductTypeService productTypeService;

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
	public JSONObject add(HttpServletRequest request, HttpSession session, ProductType productType) {

		JSONObject msgObj = new JSONObject();
		if (productType != null && productType.getName() != null
				&& StringUtils.isNotEmpty(productType.getName().trim())) {
			try {

				productTypeService.save(productType);
				msgObj.put("flag", true);
			} catch (Exception e) {
				LOGGER.error(e);
				msgObj.put("msg", "添加失败，检查名称是否已经存在！");
				msgObj.put("flag", false);
			}
		}
		return msgObj;
	}

	/**
	 * 加载修改项
	 */
	@RequestMapping(value = "/loadEdit.ajax")
	@ResponseBody
	public ProductType loadEdit(Long id) {
		try {
			return productTypeService.get(id);
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
	@RequestMapping(value = "/remove.ajax")
	@ResponseBody
	public JSONObject remove(HttpServletRequest request, HttpSession session, @RequestParam("ids") String ids) {
		JSONObject msgObj = new JSONObject();
		if (StringUtils.isNotEmpty(ids)) {
			try {
				productTypeService.delete(ids);
				msgObj.put("flag", true);
			} catch (Exception e) {
				msgObj.put("flag", false);
				msgObj.put("msg", "删除失败，该类型已经被使用不能删除！");
				LOGGER.error(e);
			}
		}
		return msgObj;
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
	@RequestMapping(value = "/edit.ajax", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject edit(HttpServletRequest request, HttpSession session, ProductType productType) {
		JSONObject msgObj = new JSONObject();
		if (productType.getId() != null) {
			try {
				ProductType updataProductType = productTypeService.get(productType.getId());
				BeanPropertyUtil.Copy(updataProductType, productType, new String[] { "name" });
				productTypeService.update(updataProductType);
				msgObj.put("flag", true);
			} catch (Exception e) {
				msgObj.put("flag", false);
				msgObj.put("msg", "修改失败，检查名称是否已经存在！");
				LOGGER.error(e);
			}
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
	public JSONObject list(HttpServletRequest request, ProductTypeQueryObject productTypeQueryObject) {
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		productTypeQueryObject.setPageSize(rows);
		Integer page = Integer.parseInt(request.getParameter("page"));
		productTypeQueryObject.setCurrentPage(page);
		JSONObject msgObj = new JSONObject();
		try {
			PageBean<ProductType> pageBean = this.productTypeService.list(productTypeQueryObject);
			msgObj.put("rows", pageBean.getRecordList());
			msgObj.put("total", pageBean.getRecordCount());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return msgObj;
	}

	@RequestMapping(value = "/listAll.ajax")
	@ResponseBody
	public List<ProductType> listAll(ProductTypeQueryObject productTypeQueryObject) {
		try {
			productTypeQueryObject.setPageSize(-1);
			return this.productTypeService.list(productTypeQueryObject).getRecordList();
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return null;
	}
}
