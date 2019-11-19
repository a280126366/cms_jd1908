package com.briup.apps.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.CategoryExample;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.CustomerException;
@Service
public class CategoryServiceImpl implements ICategoryService{
	
	@Resource
	private CategoryMapper categoryMapper;

	@Override
	public int deleteById(Long id) {
		if(categoryMapper.selectByPrimaryKey(id)==null) {
			return 0;
		}else {
			categoryMapper.deleteByPrimaryKey(id);
		}
		return 1;
	}
	
	@Override
	public void batchDelete(Long[] ids) {
		if(ids!=null&&ids.length==0) {
			throw new CustomerException("请选择要删除的栏目");
		}else {
			for (Long id : ids) {
				this.deleteById(id);
			}
		}
	}

	@Override
	public void saveOrUpdate(Category category) throws CustomerException {
		if(category.getId()!=null) {
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(category.getName());
			List<Category> list = categoryMapper.selectByExample(example);
			if(list.size()>0) {
				throw new CustomerException("栏目名称文章标题不能重复");
			}
			categoryMapper.updateByPrimaryKey(category);
		}else {
			categoryMapper.insert(category);
		}
		
	}

	@Override
	public List<Category> findAll() {
		CategoryExample example = new CategoryExample();
		return categoryMapper.selectByExample(example);
	}

	

}
