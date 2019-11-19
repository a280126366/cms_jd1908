package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.utils.CustomerException;

public interface ICategoryService {
	public int deleteById(Long id);
	public void batchDelete(Long[] id);
	public void saveOrUpdate(Category category) throws CustomerException;
	public List<Category> findAll();
}
