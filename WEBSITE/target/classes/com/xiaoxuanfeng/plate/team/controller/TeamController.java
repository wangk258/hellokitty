package com.xiaoxuanfeng.plate.team.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.xiaoxuanfeng.plate.team.bo.TeamQueryObject;
import com.xiaoxuanfeng.plate.team.domain.Team;
import com.xiaoxuanfeng.plate.team.service.TeamService;

@Controller
@RequestMapping(value = "/team")
public class TeamController extends BaseController {

	@Autowired
	private TeamService teamService;
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
	public JSONObject add(HttpServletRequest request, Team team) {
		JSONObject msgObj = new JSONObject();
		try {
			teamService.save(team);
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
	public JSONObject remove(HttpServletRequest request, @RequestParam("ids") String ids) {

		JSONObject msgObj = new JSONObject();
		try {
			if (StringUtils.isNotBlank(ids)) {
				this.teamService.delete(ids);
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
	@RequestMapping(value = "/edit.ajax", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject edit(HttpServletRequest request,Team team) {

		JSONObject msgObj = new JSONObject();
		try {
			Team updateTeam = teamService.get(team.getId());
			BeanPropertyUtil.Copy(updateTeam, team, new String[]{"name","imageUrl","description"});
			this.teamService.update(updateTeam);
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
	public JSONObject list(HttpServletRequest request, TeamQueryObject teamQueryObject) {
		JSONObject msgObj = new JSONObject();
		try {
			if (teamQueryObject == null) {
				msgObj.put("msg", MessageConstants.DATA_TRANSFORM_ERROR);
			}
			PageBean<Team> pageBean = this.teamService.list(teamQueryObject);
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
			resultFlag = fileService.upload(request, files, Constant.UPLOADFILEDIR + "/company");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println(resultFlag.getData());
	}

	/**
	 * 加载修改项
	 */
	@RequestMapping(value = "/loadEdit.ajax")
	@ResponseBody
	public Team loadEdit(Long id) {
		try {
			return teamService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
