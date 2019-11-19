package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.extend.RoleExtend;
import com.briup.apps.cms.utils.CustomerException;

import java.util.List;

public interface IRoleService {
    /** 
     *  *查询所有角色 
     */ 
    List<Role> findAll();
    
    /** 
     *  .查询角色级联权限
     */ 
    List<RoleExtend> cascadePrivilegeFindAll();
    
    /** 
     *  *保存或更新角色信息 
     */ 
    void saveOrUpdate(Role baseRole) throws CustomerException;
    
    /** 
     * * 通过id删除角色信息 
     */ 
    void deleteById(long id) throws CustomerException;
    
    /** 
     ** 授权 
     */ 
    void authorization(long roleId,List<Long> privilegeIds);
}
