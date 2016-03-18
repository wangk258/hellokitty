package com.anan.plate.article.service.impl;

import org.base.BaseDao;
import org.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anan.plate.article.dao.ArticleDao;
import com.anan.plate.article.domain.Article;
import com.anan.plate.article.service.ArticleService;
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public BaseDao<Article> getDao() {
		return articleDao;
	}
}
