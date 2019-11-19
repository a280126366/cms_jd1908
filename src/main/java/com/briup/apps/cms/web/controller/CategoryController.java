package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import io.swagger.annotations.ApiOperation;

//@Api(tags = "栏目相关接口")
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	
	@ApiOperation(value = "保存或更新")
	@PostMapping(value = "saveOrUpdate")
	public Message saveOrUpdate(Category category) {
		categoryService.saveOrUpdate(category);
		return MessageUtil.success("success");
	}
	@GetMapping(value = "deleteById")
	public Message articleDelete(Long id) {
		if(categoryService.deleteById(id)==1) {
			return MessageUtil.success("success");
		}
		return MessageUtil.error("用户名栏目不存在");
	}
	@PostMapping(value = "batchDelete")
	public Message batchDelete(@RequestParam Long[] ids) {
		categoryService.batchDelete(ids);
		return MessageUtil.success("success");
	}
	@GetMapping(value = "findAll")
	public Message findAll() {
		List<Category> list = categoryService.findAll();
		return MessageUtil.success(list);
	}
	
	
}
