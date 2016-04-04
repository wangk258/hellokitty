package com.anan.plate.webSiteCollection.controller;

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
import com.anan.plate.webSiteCollection.bo.WebSiteCollectionQueryObject;
import com.anan.plate.webSiteCollection.domain.WebSiteCollection;
import com.anan.plate.webSiteCollection.service.WebSiteCollectionService;

import common.base.BaseController;
import common.bo.PageBean;

@Controller
@RequestMapping(value = "/webSiteCollection")
public class WebSiteCollectionController extends BaseController {

	@Autowired
	private WebSiteCollectionService webSiteCollectionService;

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
	public void add(HttpServletResponse response,
			WebSiteCollection webSiteCollection) throws Exception {
		try {
			if (webSiteCollection == null) {
				resultFlag = this
						.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			if (webSiteCollection.getId() == null) {
				webSiteCollectionService.save(webSiteCollection);
			} else {
				webSiteCollectionService.update(webSiteCollection);
			}
			resultFlag = this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			resultFlag = this.setErrorFlag(e.getMessage());
		}
		this.write(response);
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
	public void delete(HttpServletResponse response,
			@RequestParam("ids") String ids) throws Exception {
		try {
			if (StringUtils.isNotBlank(ids)) {
				this.webSiteCollectionService.delete(ids);
				resultFlag = this.setRightFlag(null);
			} else {
				resultFlag = this
						.setErrorFlag(MessageConstants.SELECT_ITEM_EMPTY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultFlag = this.setErrorFlag(e.getMessage());
		}
		this.write(response);
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
	public ModelAndView list(HttpServletRequest request, HttpSession session,
			WebSiteCollectionQueryObject webSiteCollectionQueryObject) {
		ModelAndView mv = new ModelAndView("bigpage/website/website");
		try {
			if (StringUtils.isNotBlank(webSiteCollectionQueryObject.getPath())
					&& "admin".equals(webSiteCollectionQueryObject
							.getPath())) {
				mv.setViewName("admin/webSiteCollection/list");
			}
			PageBean<WebSiteCollection> pageBean = this.webSiteCollectionService
					.list(webSiteCollectionQueryObject);
			mv.addObject("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error", e.getMessage());
		}
		return mv;
	}
	/**
	 * 查询单个
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryOne")
	public ModelAndView list(HttpServletResponse response,Long id) {
		ModelAndView mv = new ModelAndView("admin/webSiteCollection/add");
		try {
			WebSiteCollection site= this.webSiteCollectionService.get(id);
			mv.addObject("site", site);
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error", e.getMessage());
		}
		return mv;
	}
	
	
}
