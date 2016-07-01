package com.anan.plate.article.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anan.plate.article.bo.ArticleQueryObject;
import com.anan.plate.article.domain.Article;
import com.anan.plate.article.service.ArticleService;
import com.anan.plate.constants.MessageConstants;
import common.bo.ResultFlag;
import common.rdbms.base.BaseController;

@Controller
@RequestMapping(value="/article",method=RequestMethod.POST)
public class ArticleController extends BaseController {

	@Autowired
	private ArticleService articleService;
	
	/**
	 * 添加
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag add(HttpServletRequest request,HttpSession session,Article article){
		try {
			if(article==null){
				this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			article.setCreateTime(new Timestamp(new Date().getTime()));
			articleService.save(article);
			return new ResultFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultFlag(false,e.getMessage());
		}
	}
	/**
	 * 删除
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag delete(HttpServletRequest request,HttpSession session,@RequestParam("ids") String ids){
		try {
			if(StringUtils.isNotBlank(ids)){
				this.articleService.delete(ids);
				return new ResultFlag(true);
			}
			else{
				return new ResultFlag(false,"请勾选删除的项目!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultFlag(false,e.getMessage());
		}
	}
	
	/**
	 * 修改
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag update(HttpServletRequest request,HttpSession session,Article article){
		try {
			if(article==null||null==article.getId()){
				return new ResultFlag(false,"数据传输错误！");
			}
			this.articleService.update(article);
			return new ResultFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultFlag(false,e.getMessage());
		}
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag list(HttpServletRequest request,HttpSession session,ArticleQueryObject articleQueryObject){
		try {
			if(articleQueryObject==null){
				return new ResultFlag(false,"数据传输错误！");
			}
			this.setRightFlag(this.articleService.list(articleQueryObject));
		} catch (Exception e) {
			if(e instanceof org.hibernate.exception.JDBCConnectionException){
				try {
					this.setRightFlag(this.articleService.list(articleQueryObject));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return new ResultFlag(false,e.getMessage());
		}
		return null;
	}
	
	/**
	 * 查询单篇文章
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/queryOne",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag list(HttpServletRequest request,HttpSession session,@RequestParam("id") Long id){
		try {
			if(id==null){
				return new ResultFlag(false,"数据传输错误！");
			}
			Article article=this.articleService.get(id);
			ResultFlag resultFlag=new ResultFlag();
			resultFlag.setFlag(true);
			resultFlag.setData(article);
			return resultFlag;
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultFlag(false,e.getMessage());
		}
	}
}
