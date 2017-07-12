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
    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
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
    public ResultBean deleteUser(String userId,String status) {
        ResultBean result =  userManage.changeUserStatus(userId,status);
        return result;
    }
}
