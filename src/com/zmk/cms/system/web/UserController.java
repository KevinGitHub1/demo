package com.zmk.cms.system.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zmk.cms.common.bean.ResultBean;
import com.zmk.cms.login.bean.User;
import com.zmk.cms.system.service.IUserManage;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserManage userManage;
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResultBean addUser(User user, HttpServletRequest request) {
        ResultBean result =  userManage.addUser(user);
        return result;
    }
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ResultBean editUser(User userInfo) {
        ResultBean result =  userManage.editUser(userInfo);
        return result;
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResultBean deleteUser(String userId) {
        ResultBean result =  userManage.deleteUser(userId);
        return result;
    }
    @RequestMapping(value = "/queryUser", method = RequestMethod.POST)
    public ResultBean queryUser(String userKey) {
        ResultBean result =  userManage.queryUser(userKey);
        return result;
    }
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public ResultBean getUserById(String userId) {
        ResultBean result =  userManage.getUserById(userId);
        return result;
    }
    @RequestMapping(value = "/changeUserStatus", method = RequestMethod.POST)
    public ResultBean changeUserStatus(String userId,String status) {
        ResultBean result =  userManage.changeUserStatus(userId,status);
        return result;
    }
    @RequestMapping(value = "/queryOrganization", method = RequestMethod.POST)
    public ResultBean queryOrganization(String type ,String key) {
        ResultBean result =  userManage.queryOrganization(type,key);
        return result;
    }
    @RequestMapping(value = "/queryOrgaTree", method = RequestMethod.GET)
    public ResultBean queryOrgaTree() {
        ResultBean result =  userManage.queryOrgaTree();
        return result;
    }
    @RequestMapping(value = "/addOrg", method = RequestMethod.POST)
    public ResultBean addOrg(String orgname,String orgno) {
        ResultBean result =  userManage.addOrg(orgname,orgno);
        return result;
    }
    @RequestMapping(value = "/addDept", method = RequestMethod.POST)
    public ResultBean addDept(String deptname,String deptno,String parentid) {
        ResultBean result =  userManage.addDept(deptname,deptno,parentid);
        return result;
    }
    @RequestMapping(value = "/deleteOrg", method = RequestMethod.POST)
    public ResultBean deleteOrg(String id) {
        ResultBean result =  userManage.deleteOrg(id);
        return result;
    }
    @RequestMapping(value = "/editOrg", method = RequestMethod.POST)
    public ResultBean editOrg(String id,String orgname,String orgno) {
        ResultBean result =  userManage.editOrg(id,orgname,orgno);
        return result;
    }
}
