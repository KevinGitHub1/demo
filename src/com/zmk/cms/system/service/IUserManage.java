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
    /**
     * 
     * 查询组织机构信息
     * @param type 1：查询所有数据；2:根据上级单位ID查询；3：根据单位名称模糊查询
     * @param key
     * @return
     * @return	ResultBean
     * @exception	例外
     */
    public ResultBean queryOrganization(String type,String key);
    public ResultBean queryOrgaTree();
    public ResultBean addOrg(String orgname, String orgno);
    public ResultBean addDept(String deptname, String deptno, String parentid);
    public ResultBean deleteOrg(String id);
    public ResultBean editOrg(String id, String orgname, String orgno);
}
