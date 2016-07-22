package com.anan.plate.index.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anan.plate.diary.service.DiaryService;
import com.anan.plate.english.service.EnglishService;
import com.anan.plate.photo.service.AlbumService;
import com.anan.plate.webSiteCollection.service.WebSiteCollectionService;

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
		
		return null;
	}
}
