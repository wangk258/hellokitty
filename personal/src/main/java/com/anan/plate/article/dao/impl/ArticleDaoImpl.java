package com.anan.plate.article.dao.impl;

import org.springframework.stereotype.Repository;

import com.anan.plate.article.dao.ArticleDao;
import com.anan.plate.article.domain.Article;
import common.rdbms.base.BaseDaoImpl;
@Repository
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao{

}
