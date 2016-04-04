package com.xiaoxuanfeng.plate.navigation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xiaoxuanfeng.plate.common.util.BeanPropertyUtil;
import com.xiaoxuanfeng.plate.navigation.bo.NavigationQueryObject;
import com.xiaoxuanfeng.plate.navigation.domain.Navigation;
import com.xiaoxuanfeng.plate.navigation.service.NavigationService;

import common.base.BaseController;
import common.bo.PageBean;

@Controller
@RequestMapping(value = "/navigation")
public class NavigationController extends BaseController {

	private final static Logger LOGGER = Logger.getLogger(Logger.class);
	@Autowired
	private NavigationService navigationService;

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
	public JSONObject add(Navigation navigation) {
		
		JSONObject msgObj = new JSONObject();
		if (navigation != null) {
			if (navigation.getId() == null) {
				try {
					
					Integer maxSort = navigationService.executeBySQL("select max(sort) from Navigation");
					navigation.setSort(maxSort+1);
					navigationService.save(navigation);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					navigationService.update(navigation);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		msgObj.put("flag", true);
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
	public JSONObject remove(HttpServletRequest request, @RequestParam("ids") String ids) {
		JSONObject msgObj = new JSONObject();
		try {
			//navigationService.delete(ids);
			navigationService.deleteAndUpdataSort(ids);
			msgObj.put("flag", true);
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
	@RequestMapping(value = "/loadEdit", method = RequestMethod.GET)
	@ResponseBody
	public Navigation loadEdit(Long id) {
		try {
			return navigationService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/edit.ajax", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject edit(HttpServletRequest request, Navigation navigation) {
		JSONObject msgObj = new JSONObject();
		try {
			Navigation updateNavigation = navigationService.get(navigation.getId());
			BeanPropertyUtil.Copy(updateNavigation, navigation, new String[] { "name", "url","page" });
			navigationService.update(updateNavigation);
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
	@RequestMapping(value = "/list.ajax")
	@ResponseBody
	public JSONObject list(HttpServletRequest request, NavigationQueryObject navigationQueryObject) {

		Integer rows = Integer.parseInt(request.getParameter("rows"));
		navigationQueryObject.setPageSize(rows);
		Integer page = Integer.parseInt(request.getParameter("page"));
		navigationQueryObject.setCurrentPage(page);

		JSONObject msgObj = new JSONObject();
		try {
			PageBean<Navigation> pageBean = this.navigationService.list(navigationQueryObject);
			msgObj.put("rows", pageBean.getRecordList());
			if(page!=-1)
			msgObj.put("total", pageBean.getRecordCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgObj;
	}

	@RequestMapping(value = "/changeSort.ajax")
	@ResponseBody
	public JSONObject changeSort(Long id, Integer loop) {

		JSONObject msgObj = new JSONObject();
		Integer changeValue = 0;
		if (null != id && null != loop) {
			try {
				Navigation navigation = navigationService.get(id);
				// 当前要修改的排序
				changeValue = navigation.getSort() + loop;

				Navigation tagNavigation = navigationService.get(" from Navigation where sort =?", changeValue);
				tagNavigation.setSort(navigation.getSort());
				navigation.setSort(changeValue);
				navigationService.update(navigation);
				navigationService.update(tagNavigation);
				msgObj.put("flag", true);
			} catch (Exception e) {
				msgObj.put("flag", false);
				LOGGER.error(e);
			}

		}
		return msgObj;
	}
	@RequestMapping(value = "/ajaxList.ajax")
	@ResponseBody
	public JSONObject ajaxList() {
		JSONObject msgArr = new JSONObject();
			try {

				List<Navigation> tagNavigation = navigationService.list();
				for (Navigation navigation : tagNavigation) {
					JSONObject json = new JSONObject();
					json.put("id",navigation.getId() );
					json.put("name",navigation.getName());
					msgArr.put(navigation.getId().toString(), json);
				}
			} catch (Exception e) {
				LOGGER.error(e);
			}

		return msgArr;
	}
}
