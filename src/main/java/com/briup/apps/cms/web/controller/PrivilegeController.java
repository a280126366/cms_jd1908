package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.service.IPrivilegeService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.vm.PrivilegeTree;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: cms_jd1911
 * @description: 权限控制器
 * @author: charles
 * @create: 2019-11-16 15:51
 **/
@Validated
@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

    @Autowired
    private IPrivilegeService PrivilegeService;

    @ApiOperation(value = "查询所有")
    @GetMapping(value = "findAll")
    public Message findAll(){
        List<Privilege> list = PrivilegeService.findAll();
        return MessageUtil.success(list);
    }

    @ApiOperation(value = "通过Id删除")
    @GetMapping(value = "deleteById")
    public Message deleteById(Long id){
    	PrivilegeService.deleteById(id);
        return MessageUtil.success("删除成功");
    }

    @ApiOperation(value = "通过parentId查询")
    @GetMapping(value = "findByParentId")
    public Message findByParentId(Long id){
        List<Privilege> list = PrivilegeService.findByParentId(id);
        return MessageUtil.success(list);
    }

    @ApiOperation(value ="保存或更新")
    @PostMapping(value = "saveOrUpdate")
    public Message saveOrUpdate(Privilege privilege){
        PrivilegeService.saveOrUpdate(privilege);
        return MessageUtil.success("更新成功");
    }

    @ApiOperation(value = "查询树")
    @GetMapping(value = "findPrivilegeTree")
    public Message findPrivilegeTree(){
        List<PrivilegeTree> list = PrivilegeService.findPrivilegeTree();
        return MessageUtil.success(list);
    }



}
