package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.RoleAndPrivilege;
import com.briup.apps.cms.bean.RoleAndPrivilegeExample;
import com.briup.apps.cms.bean.RoleExample;
import com.briup.apps.cms.bean.extend.RoleExtend;
import com.briup.apps.cms.dao.RoleAndPrivilegeMapper;
import com.briup.apps.cms.dao.RoleMapper;
import com.briup.apps.cms.dao.extend.RoleExtendMapper;
import com.briup.apps.cms.service.IRoleService;
import com.briup.apps.cms.utils.CustomerException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 **角色实现类
 **/
@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleExtendMapper roleExtendMapper;
    @Resource
    private RoleAndPrivilegeMapper roleAndPrivilegeMapper;
    
    
    @Override
    public List<Role> findAll() {

        return roleMapper.selectByExample(new RoleExample());
    }

    @Override
    public List<RoleExtend> cascadePrivilegeFindAll() {
        return roleExtendMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(Role Role) throws CustomerException {
        if(Role.getId()!=null){
            roleMapper.updateByPrimaryKey(Role);
        } else {
            roleMapper.insert(Role);
        }
    }

    @Override
    public void deleteById(long id) throws CustomerException {
        Role role = roleMapper.selectByPrimaryKey(id);
        if(role == null){
            throw new CustomerException("要删除的角色不存在");
        }
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void authorization(long roleId, List<Long> privilegeIds) {
        // 根据roleid查询出所有的权限
        RoleAndPrivilegeExample example = new RoleAndPrivilegeExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<RoleAndPrivilege> list = roleAndPrivilegeMapper.selectByExample(example);
        // 将list转换为privilegeIDs的集合
        List<Long> old_privilegeIds = new ArrayList<>();
        for(RoleAndPrivilege rp : list){
            old_privilegeIds.add(rp.getPrivilegeId());
        }
        // 依次判断privilegeIds 是否存在old_privilegeIds，如果不在则插入
        for(long privilegeId : privilegeIds){
            if (!old_privilegeIds.contains(privilegeId)) {
                RoleAndPrivilege rp = new RoleAndPrivilege();
                rp.setRoleId(roleId);
                rp.setPrivilegeId(privilegeId);
                roleAndPrivilegeMapper.insert(rp);
            }
        }
        // 依次判断 是否存在old_privilegeIds 是否存在privilegeIds，如果不存在删除
        for(long privilegeId: old_privilegeIds){
            if(!privilegeIds.contains(privilegeId)){
                // 根据privilegeId 从桥表中删除
                example.clear();
                example.createCriteria()
                        .andRoleIdEqualTo(roleId)
                        .andPrivilegeIdEqualTo(privilegeId);
                roleAndPrivilegeMapper.deleteByExample(example);
            }
        }

    }
}
