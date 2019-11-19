package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.UserExample;
import com.briup.apps.cms.bean.UserAndRole;
import com.briup.apps.cms.bean.UserAndRoleExample;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.dao.UserMapper;
import com.briup.apps.cms.dao.UserAndRoleMapper;
import com.briup.apps.cms.dao.extend.UserExtendMapper;
import com.briup.apps.cms.service.IUserService;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.UserVM;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @program: cms_jd1911
 * @description: 用户业务实现类
 * @author: charles
 * @create: 2019-11-15 15:18
 **/
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserExtendMapper userExtendMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserAndRoleMapper userAndRoleMapper;
    
    @Override
    public User login(UserVM userVM) throws CustomerException {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(userVM.getUsername());
        List<User> list = userMapper.selectByExample(example);
        if(list.size()<=0){
            throw new CustomerException("该用户不存在");
        }
        User user = list.get(0);
        if(!user.getPassword().equals(userVM.getPassword())){
            throw new CustomerException("密码不匹配");
        }
        return user;

    }

    @Override
    public UserExtend findById(long id) {
        return userExtendMapper.selectById(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public List<UserExtend> cascadeRoleFindAll() {
        return userExtendMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(User User) throws CustomerException {
        if(User.getId()!=null){
            userMapper.updateByPrimaryKey(User);
        } else {
            // 判断用户名是否被占用
            UserExample example = new UserExample();
            example.createCriteria().andUsernameEqualTo(User.getUsername());
            List<User> list = userMapper.selectByExample(example);
            if(list.size()>0){
                throw new CustomerException("该用户已经被占用");
            }
            // 初始化
            User.setRegisterTime(new Date().getTime());
            User.setStatus(UserExtend.STATUS_NORMAL);
            userMapper.insert(User);
        }
    }

    @Override
    public void changeStatus(long id,String status) throws CustomerException {
        User user = this.findById(id);
        if(user==null){
            throw new CustomerException("该用户不存在");
        }
        user.setStatus(status);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteById(long id) throws CustomerException {
        User user = this.findById(id);
        if(user==null){
            throw new CustomerException("该用户不存在");
        }
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void setRoles(long id, List<Long> roles) {
        // 根据userid查询自己的角色
        UserAndRoleExample example = new UserAndRoleExample();
        example.createCriteria().andUserIdEqualTo(id);
        // 用户角色关系,获取所有老的角色
        List<UserAndRole> list = userAndRoleMapper.selectByExample(example);
        List<Long> oldRoles = new ArrayList<>();
        Iterator<UserAndRole> iterator = list.iterator();
        while(iterator.hasNext()){
            oldRoles.add(iterator.next().getRoleId());
        }
        // [1,2,3] -> [3,4] 添加1,2 => [1,2,3,4]
        // 依次判断新角色是否存在于list中，如果不存在则添加
        for(Long roleId : roles){
            if(!oldRoles.contains(roleId)){
                UserAndRole userAndRole = new UserAndRole();
                userAndRole.setRoleId(roleId);
                userAndRole.setUserId(id);
                userAndRoleMapper.insert(userAndRole);
            }
        }
        // [1,2,3] -> [1,2,3,4]   删除 3,4  => [1,2]
        // 一次判断老的角色是否存在于roles中，如果不存在则删除
        for(UserAndRole UserAndRole : list){
            if(!roles.contains(UserAndRole.getRoleId())){
				//UserAndRoleExample example1 = new UserAndRoleExample();
                userAndRoleMapper.deleteByPrimaryKey(UserAndRole.getId());
            }
        }


    }

}
