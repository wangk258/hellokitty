package com.xiaoxuanfeng.plate.product.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;
import org.util.FileUploadUtil;

import com.alibaba.fastjson.JSONObject;
import com.xiaoxuanfeng.plate.common.constants.Constant;
import com.xiaoxuanfeng.plate.common.util.BeanPropertyUtil;
import com.xiaoxuanfeng.plate.common.util.RequestUtil;
import com.xiaoxuanfeng.plate.product.bo.ProductQueryObject;
import com.xiaoxuanfeng.plate.product.domain.Product;
import com.xiaoxuanfeng.plate.product.service.ProductService;
import com.xiaoxuanfeng.plate.productType.domain.ProductType;
import com.xiaoxuanfeng.plate.productType.service.ProductTypeService;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController {

	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductTypeService productTypeService;
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
	public JSONObject add(HttpServletRequest request, Product product) {

		JSONObject msgObj = new JSONObject();
		try {
			updateImageUrl(product, request);
			if (product.getProductType() != null && product.getProductType().getId() != null) {
				ProductType productType = productTypeService.get(product.getProductType().getId());
				product.setProductType(productType);
			} else {
				product.setProductType(null);
			}
			productService.save(product);
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
	@RequestMapping(value = "/remove.ajax", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject remove(@RequestParam("ids") String ids) {
		JSONObject msgObj = new JSONObject();
		try {
			if (StringUtils.isNotBlank(ids)) {
				productService.delete(ids);
				msgObj.put("flag", true);
			}
		} catch (Exception e) {
			msgObj.put("flag", false);
			e.printStackTrace();
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
	@RequestMapping(value = "/loadEdit.ajax", method = RequestMethod.GET)
	@ResponseBody
	public Product loadEdit(Long id) {
		try {
			if (id != null) {
				return productService.get(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/edit.ajax", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject edit(HttpServletRequest request, Product product) {
		JSONObject msgObj = new JSONObject();
		try {
			Product updateProduct = productService.get(product.getId());
			BeanPropertyUtil.Copy(updateProduct, product, new String[] { "name", "description", "imageUrl", "price","year","month","material" });
			if (product.getProductType() != null && product.getProductType().getId() != null) {
				ProductType productType = productTypeService.get(product.getProductType().getId());
				updateProduct.setProductType(productType);
			} else {
				updateProduct.setProductType(null);
			}
			productService.update(updateProduct);
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
	public JSONObject list(HttpServletRequest request, HttpSession session, ProductQueryObject productQueryObject)
			throws Exception {
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		productQueryObject.setPageSize(rows);
		Integer page = Integer.parseInt(request.getParameter("page"));
		productQueryObject.setCurrentPage(page);
		JSONObject msgObj = new JSONObject();
		try {
			PageBean<Product> pageBean = this.productService.list(productQueryObject);
			msgObj.put("rows", pageBean.getRecordList());
			
			msgObj.put("total", pageBean.getRecordCount());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return msgObj;
	}

	@RequestMapping(value = "/loadCheckedCount.ajax", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadCheckedCount() {
		JSONObject msgObj = new JSONObject();
		try {
			msgObj.put("total", productService.executeBySQL("select count(*) from Product where showHomePage = 1 "));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgObj;
	}

	@RequestMapping(value = "/updateProductShowState.ajax", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject updateProductShowState(Long id, Integer state) {
		JSONObject msgObj = new JSONObject();
		try {
			Product product = productService.get(id);
			if (product != null) {
				product.setShowHomePage(state);
				productService.update(product);
				msgObj.put("flag", true);
			}
		} catch (Exception e) {
			msgObj.put("flag", false);
			e.printStackTrace();
		}
		return msgObj;
	}

	@RequestMapping(value = "/uploadFile.ajax")
	public void uploadFile(MultipartRequest request, HttpServletResponse response) throws Exception {
		try {
			MultipartFile files = request.getFile("filedata");
			resultFlag = fileService.upload(request, files, Constant.UPLOADFILEDIR + "/product");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println(resultFlag.getData());
	}

	@RequestMapping(value = "/product.php")
	public ModelAndView getOne(Long id) {
		ModelAndView view = new ModelAndView("front/product");
		try {
			view.addObject("product", this.productService.get(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	@RequestMapping(value = "/list.php")
	public ModelAndView list(ProductQueryObject pq, HttpSession session) {
		ModelAndView view = new ModelAndView("front/productlist");
		session.setAttribute("navid", pq.getPkey());
		try {
			pq.setCurrentPage(-1);
			List<Product> products=this.productService.list(pq).getRecordList();
			Map<String,List<Product>> map=new HashMap<String,List<Product>>();
			for (Product product : products) {
				if(map.containsKey(product.getMonth())){
					List<Product> list=map.get(product.getMonth());
					list.add(product);
					map.put(product.getMonth(), list);
				}
				else{
					List<Product> sub=new ArrayList<Product>();
					sub.add(product);
					map.put(product.getMonth(),sub);
					sub=null;
				}
			}
			view.addObject("map", map);
			view.addObject("categorys", this.productTypeService.list("from ProductType"));
			view.addObject("key", pq.getName());
			if(StringUtils.isNotBlank(pq.getMonth())){
				view.addObject("month",Integer.parseInt((pq.getMonth())));
			}
			else{
				view.addObject("month",-1);
			}
			if(StringUtils.isBlank(pq.getYear())){
				view.addObject("year",Calendar.getInstance().get(Calendar.YEAR));
			}
			else{
				view.addObject("year",pq.getYear());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	private void updateImageUrl(Product product, HttpServletRequest request) throws Exception {
		if (StringUtils.isNotEmpty(product.getImageUrl()))
			product.setImageUrl(product.getImageUrl().replace(RequestUtil.getBasePath(request), ""));
	}

	public static void main(String[] args) {
		String s1 = "http://localhost:8080", s2 = "http://localhost:8080/upload/aa";
		System.out.println(s2.replace(s1, ""));
		System.out.println(Calendar.getInstance().get(Calendar.YEAR));
	}

}
