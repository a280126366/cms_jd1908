package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.utils.CustomerException;

public interface IArticleService {
	public int deleteById(Long id);
	public void saveOrUpdate(Article article) throws CustomerException;
	public List<Article> findAll();
	public List<ArticleExtend> findArticleExtendAll();
	public Article findArticleById(Long id);
	public ArticleExtend findArticleExtendById(Long id);
}
