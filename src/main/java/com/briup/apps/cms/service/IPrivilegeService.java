package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.PrivilegeTree;

import java.util.List;

public interface IPrivilegeService {
    
    /** 
     *  查询所有权限 
     */ 
    List<Privilege> findAll();
    
    /** 
     * .通过parentId查询权限
     */ 
    List<Privilege> findByParentId(Long parentId);
    
    /** 
     *  .保存或更新权限 
     */ 
    void saveOrUpdate(Privilege privilege) throws CustomerException;

    /** 
     **查询权限树 
     */ 
    List<PrivilegeTree> findPrivilegeTree();
    
    /** 
     **查询用户所有权限 
     */ 
    List<Privilege> findByUserId(long id);
    /** 
     * .通过id删除权限
     */ 
    void deleteById(Long id);

}
