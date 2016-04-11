package com.anan.plate.music.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.anan.plate.constants.MessageConstants;
import com.anan.plate.music.domain.Music;
import com.anan.plate.music.service.MusicService;
import common.bo.PageBean;
import common.bo.ResultFlag;
import common.rdbms.base.BaseController;

@Controller
@RequestMapping(value="/music")
public class MusicController  extends BaseController {

	@Autowired
	private MusicService musicService;
	
	private static final List<Music> musics=new ArrayList<Music>();
	
	private static final int pageSize=10;
	
	/**
	 * 添加
	 * @param request
	 * @param session
	 * @param ptMail
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public ResultFlag add(HttpServletRequest request,HttpSession session,Music music){
		try {
			if(music==null){
				return this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			musicService.save(music);
			return this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setErrorFlag(e.getMessage());
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
				this.musicService.delete(ids);
				return this.setRightFlag(null);
			}
			else{
				return this.setErrorFlag(MessageConstants.SELECT_ITEM_EMPTY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.setErrorFlag(e.getMessage());
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
	public ResultFlag update(HttpServletRequest request,HttpSession session,Music music){
		try {
			if(music==null||null==music.getId()){
				return this.setErrorFlag(MessageConstants.DATA_TRANSFORM_ERROR);
			}
			this.musicService.update(music);
			return this.setRightFlag(null);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setErrorFlag(e.getMessage());
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
	@RequestMapping(value = "/list")
	public ModelAndView list(HttpSession session,String key,Integer currentPage){
		ModelAndView mv=new ModelAndView("bigpage/music/list");
		List<Music> pageData=new ArrayList<Music>();
		try {
			if(StringUtils.isNotBlank(key) || musics.isEmpty()){
				key=URLEncoder.encode(key,"utf-8");
				URL url = new URL("http://cloud.kugou.com/app/getSearchResult.php?key={"+key+"}&pageNo={1}&pageSize={10}");
				InputStreamReader isr = new InputStreamReader(url.openStream());
				BufferedReader br = new BufferedReader(isr);
				String s;
				StringBuilder sb=new StringBuilder();
				while((s=br.readLine())!=null){
					sb.append(s);
				}
				br.close();
				isr.close();
				s=sb.toString();
				s.replace("\"", "'");
				JSONObject jo=JSONObject.fromObject(s);
				if(jo.getJSONArray("data").size()>1){
					musics.clear();
					JSONArray datas=jo.getJSONArray("data");
					int length=jo.getJSONArray("data").size();
					for (int i = 0; i < length; i++) {
						Music music=new Music();
						music.setSongName(datas.getJSONObject(i).get("FileName").toString());
						music.setSinger(datas.getJSONObject(i).get("SingerName").toString());
						music.setHash("http://cloud.kugou.com/singlePlayer/4/362/1/0/"+datas.getJSONObject(i).get("Hash").toString()+".swf");
						musics.add(music);
					}
				}
				else{
					mv.addObject("error","无搜索结果！");
				}
			}
			System.out.println(musics.size());
			if(null==currentPage){
				currentPage=1;
			}
			int begin=(currentPage-1)*pageSize;
			int end=currentPage*pageSize-1;
			if(end>musics.size()-1){
				end=musics.size()-1;
			}
			for (int i = begin; i <=end; i++) {
				pageData.add(musics.get(i));
			}
			mv.addObject("musics",pageData);
			PageBean<Music> pg=new PageBean<Music>(currentPage,pageSize,musics.size());
			mv.addObject("pageBean",pg);
			if(StringUtils.isNotBlank(key)){
				mv.addObject("key",URLDecoder.decode(key, "utf-8"));
			}
		} catch (Exception e) {
			mv.addObject("error",e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
}
