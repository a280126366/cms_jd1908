package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.extend.RoleExtend;

import java.util.List;



public interface RoleExtendMapper {
    List<Role> selectByUserId(long id);

    List<RoleExtend> selectAll();
}
