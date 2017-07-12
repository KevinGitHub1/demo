package com.zmk.cms.login.service;

import java.util.Map;

import com.zmk.cms.login.bean.User;

/**
 * 
 * ClassName: ILoginServices
 * 
 * @Description: 登录相关接口定义
 * @author zhangmk
 * @date 2017-4-27
 */
public interface ILoginServices {
    /**
     * 
     * 检查用户是否为本系统用户，如果是则可以登录系统， 如果不是则禁止登录
     * 
     * @param user
     * @return Map<String,Object>
     * @exception 例外
     */
    public Map<String, Object> CheckUser(User user);

}