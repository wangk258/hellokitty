package com.xiaoxuanfeng.plate.editor.controller;

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
import com.xiaoxuanfeng.plate.editor.bo.EditorQueryObject;
import com.xiaoxuanfeng.plate.editor.domain.Editor;
import com.xiaoxuanfeng.plate.editor.service.EditorService;

@Controller
@RequestMapping(value = "/editor")
public class EditorController extends BaseController {

	private final static Logger LOGGER = Logger
			.getLogger(EditorController.class);

	@SuppressWarnings("unused")
	private Map<Integer, String> types = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0, "编辑");
			put(1, "维护");
		}
	};
	@Autowired
	private EditorService editorService;
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
	@RequestMapping(value = "/addMenu.ajax", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addMenu(HttpServletRequest request, HttpSession session,
			Editor editor) {

		JSONObject msgObj = new JSONObject();

		try {
			Editor updateEditor = editorService.get(
					" from Editor where ckey= ? and pkey = ?",
					editor.getCkey(), editor.getPkey());
			if (updateEditor != null) {
				BeanPropertyUtil.Copy(updateEditor, editor, new String[] {
						"ckey", "pkey" });
				editorService.update(updateEditor);
			} else {
				editorService.save(editor);
			}
			msgObj.put("flag", true);
		} catch (Exception e) {
			msgObj.put("flag", false);
			e.printStackTrace();
		}
		return msgObj;
	}

	@RequestMapping(value = "/editMenu.ajax", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject editMenu(HttpServletRequest request, HttpSession session,
			Editor editor) {

		JSONObject msgObj = new JSONObject();

		try {
			Editor updateEditor = editorService.get(editor.getId());
			if (updateEditor != null) {
				BeanPropertyUtil.Copy(updateEditor, editor, new String[] {
						"ckey", "pkey" });
				editorService.update(updateEditor);
			} else {
				editorService.save(editor);
			}
			msgObj.put("flag", true);
		} catch (Exception e) {
			msgObj.put("flag", false);
			e.printStackTrace();
		}
		return msgObj;
	}

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
	public JSONObject add(HttpServletRequest request, HttpSession session,
			Editor editor) {

		JSONObject msgObj = new JSONObject();

		// 验证key不能为空
		if (editor == null || editor.getPkey() == null
				|| StringUtils.isEmpty(editor.getCkey())) {
			msgObj.put("flag", false);
			return msgObj;
		}

		try {
			Editor updateEditor = editorService.get(
					" from Editor where ckey= ? and pkey = ?",
					editor.getCkey(), editor.getPkey());
			if (updateEditor != null) {
				BeanPropertyUtil.Copy(updateEditor, editor,
						new String[] { "content" });
				editorService.update(updateEditor);
			} else {
				editorService.save(editor);
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
	public Editor loadEdit(String ckey, Long pkey) {
		try {
			if (pkey != null && StringUtils.isNotEmpty(ckey)) {
				Editor editor = editorService.get(
						" from Editor where ckey like  ? and pkey = ?", "%"
								+ ckey + "%", pkey);
				return editor;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加载修改项
	 */
	@RequestMapping(value = "/loadEditById.ajax")
	@ResponseBody
	public Editor loadEditById(Long id) {
		try {
			if (id != null)
				return editorService.get(" from Editor  where id = ?", id);
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
	public JSONObject remove(@RequestParam("ids") String ids) {

		JSONObject msgObj = new JSONObject();
		try {
			if (StringUtils.isNotBlank(ids)) {
				this.editorService.delete(ids);
				msgObj.put("flag", true);
			}
		} catch (Exception e) {
			LOGGER.error(e);
			msgObj.put("flag", false);
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
	public JSONObject list(HttpServletRequest request, HttpSession session,
			EditorQueryObject editorQueryObject) {
		JSONObject msgObj = new JSONObject();
		try {
			Integer rows = Integer.parseInt(request.getParameter("rows"));
			editorQueryObject.setPageSize(rows);
			Integer page = Integer.parseInt(request.getParameter("page"));
			editorQueryObject.setCurrentPage(page);
			PageBean<Editor> pageBean = editorService.list(editorQueryObject);
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
					Constant.UPLOADFILEDIR + "/editor");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println(resultFlag.getData());
	}

	@RequestMapping(value = "/rainbow.php")
	public ModelAndView content(String ckey, Long pkey, String page,
			HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("front/" + page);
		try {
			Editor editor=null;
			if(StringUtils.isNotBlank(ckey)){
				editor = this.editorService.get(
						"from Editor where  pkey = ? and ckey like ?", pkey, "%"
								+ ckey + "%");
			}
			else{
				List<Editor> list=this.editorService.list("from Editor where  pkey = ?",pkey);
				if(!list.isEmpty()){
					editor=list.get(0);
				}
			}
			if (null == editor) {
				editor=new Editor();
				editor.setContent("暂无内容");
			}
			view.addObject("editor",editor);
			session.setAttribute("navid", pkey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
}
