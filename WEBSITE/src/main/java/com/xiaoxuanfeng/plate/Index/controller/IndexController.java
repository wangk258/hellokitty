package com.xiaoxuanfeng.plate.Index.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoxuanfeng.plate.banner.service.BannerService;
import com.xiaoxuanfeng.plate.product.service.ProductService;
import common.rdbms.base.BaseController;

@Controller
public class IndexController  extends BaseController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private BannerService bannerService;
	
	/**
	 * 加载主页
	 * @return
	 */
	@RequestMapping(value = "/index.php")
	public ModelAndView list(Long pkey,HttpSession session){
		ModelAndView view=new ModelAndView("front/index");
		try {
			session.setAttribute("navid", 1);
			view.addObject("banners",this.bannerService.list("from Banner where showHomePage=1 "));
			view.addObject("products",this.productService.list("from Product where showHomePage=1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
}
