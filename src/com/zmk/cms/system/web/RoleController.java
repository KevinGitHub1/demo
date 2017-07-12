package com.zmk.cms.system.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zmk.cms.common.bean.ResultBean;
import com.zmk.cms.system.service.IRoleManage;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleManage RoleManage;
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public ResultBean addRole(String roleName,String user ) {
        ResultBean result =  RoleManage.addRole(roleName,user);
        return result;
    }
    @RequestMapping(value = "/editRole", method = RequestMethod.POST)
    public ResultBean editRole(String roleId,String roleName,String user) {
        ResultBean result =  RoleManage.editRole(roleId,roleName,user);
        return result;
    }
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    public ResultBean deleteRole(String RoleId) {
        ResultBean result =  RoleManage.deleteRole(RoleId);
        return result;
    }
    @RequestMapping(value = "/queryRole", method = RequestMethod.GET)
    public ResultBean queryRole(String RoleKey) {
        ResultBean result =  RoleManage.queryRole(RoleKey);
        return result;
    }
}
