package com.briup.apps.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleExample;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.dao.extend.ArticleExtendMapper;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.CustomerException;
@Service
public class ArticleServiceImpl implements IArticleService{

	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private ArticleExtendMapper articleExtendMapper;

	@Override
	public int deleteById(Long id) {
		if(articleMapper.selectByPrimaryKey(id)==null) {
			return 0;
		}else {
			articleMapper.deleteByPrimaryKey(id);
		}
		return 1;
	}


	@Override
	public void saveOrUpdate(Article article) throws CustomerException{
		if(article.getId()!=null) {
			articleMapper.updateByPrimaryKey(article);
		}else {
			ArticleExample example = new ArticleExample();
			example.createCriteria().andTitleEqualTo(article.getTitle());
			List<Article> list = articleMapper.selectByExample(example);
			if(list.size()>0) {
				throw new CustomerException("文章标题不能重复");
			}
			
			//初始化
			article.setStatus(ArticleExtend.STATUS_UNCHECK);
			article.setThumpUp(0l);
			article.setThumpDown(0l);
			article.setPublishTime(new Date().getTime());
			article.setReadTimes(0l);
			articleMapper.insert(article);
		}
		
	}

	@Override
	public List<Article> findAll() {
		ArticleExample articleExample = new ArticleExample();
		return articleMapper.selectByExample(articleExample);
	}

	@Override
	public Article findArticleById(Long id) {
		return articleMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<ArticleExtend> findArticleExtendAll() {
		return articleExtendMapper.selectArticleExtendAll();
	}


	@Override
	public ArticleExtend findArticleExtendById(Long id) {
		return articleExtendMapper.selectArticleExtendById(id);
	}



	

}
