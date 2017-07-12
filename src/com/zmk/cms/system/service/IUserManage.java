package com.zmk.cms.system.service;

import com.zmk.cms.common.bean.ResultBean;
import com.zmk.cms.login.bean.User;

/**
 * 
 * <p>Title: IUserManage</p>
 * <p>Description: 用户管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * @author    zmk
 * @version   v0.1
 */
public interface IUserManage {
    /**
     * 
     * 新增用户信息
     * @param user
     * @return	Map<String,Object>
     * @exception	例外
     */
    public ResultBean addUser(User user);
    /**
     * 
     * 编辑用户信息
     * @param user
     * @return	Map<String,Object>
     * @exception	例外
     */
    public ResultBean editUser(User user);
    /**
     * 
     * 根据用户ID删除用户信息
     * @param userId
     * @return
     * @return	Map<String,Object>
     * @exception	例外
     */
    public ResultBean deleteUser(String userId);
    /**
     * 
     * 根据用户信息关键字检索用户信息
     * @param userKey
     * @return
     * @return	Map<String,Object>
     * @exception	例外
     */
    public ResultBean queryUser(String userKey);
    public ResultBean getUserById(String userId);
    public ResultBean changeUserStatus(String userId, String status);
}
