package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.UserVM;

public interface IUserService {
	/** 
     **登录
     */
    User login(UserVM userVM) throws CustomerException;
	/**
     **通过id查询
     */
    UserExtend findById(long id);

    /**
     **查询所有
     */
    List<User> findAll();
    
    /** 
     **级联查询所有
     */ 
    List<UserExtend> cascadeRoleFindAll();

    /**
     **保存或更新
     */
    void saveOrUpdate(User User) throws CustomerException;

    /** 
     **更新状态
     */ 
    void changeStatus(long id,String status) throws CustomerException;
    
    /** 
     **通过id删除
     */ 
    void deleteById(long id) throws CustomerException;
    
    /** 
     **设置角色
     */ 
    void setRoles(long id, List<Long> roles);
}
