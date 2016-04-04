package com.anan.plate.index.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anan.plate.diary.bo.DiaryQueryObject;
import com.anan.plate.diary.service.DiaryService;
import com.anan.plate.english.bo.EnglishQueryObject;
import com.anan.plate.english.service.EnglishService;
import com.anan.plate.photo.bo.AlbumQueryObject;
import com.anan.plate.photo.service.AlbumService;
import com.anan.plate.webSiteCollection.bo.WebSiteCollectionQueryObject;
import com.anan.plate.webSiteCollection.service.WebSiteCollectionService;

import common.bo.QueryObject;

@Controller
public class IndexController {

	@Autowired
	private DiaryService diaryService;
	@Autowired
	private WebSiteCollectionService webSiteCollectionService;
	@Autowired
	private EnglishService englishService;
	@Autowired
	private AlbumService alubmService;

	@RequestMapping("/index")
	public ModelAndView index() throws Exception {
		ModelAndView mv = new ModelAndView("index");
		Integer pageSize=5;
		QueryObject good = new DiaryQueryObject();
		good.setPageSize(pageSize);
		good.setCurrentPage(1);
		mv.addObject("diarys", this.diaryService.list(good));
		
		good = new WebSiteCollectionQueryObject();
		good.setPageSize(pageSize);
		good.setCurrentPage(1);
		mv.addObject("websites", this.webSiteCollectionService.list(good));
		
		good=new EnglishQueryObject();
		good.setPageSize(pageSize);
		good.setCurrentPage(1);
		mv.addObject("englishs",this.englishService.list(good));
		
		good=new AlbumQueryObject();
		good.setPageSize(pageSize);
		good.setCurrentPage(1);
		mv.addObject("albums",this.alubmService.list(good));
		return mv;
	}
}
