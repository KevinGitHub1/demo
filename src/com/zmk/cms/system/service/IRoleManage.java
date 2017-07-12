package com.zmk.cms.system.service;

import com.zmk.cms.common.bean.ResultBean;

/**
 * 
 * <p>Title: IRoleManage</p>
 * <p>Description: 角色管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * @author    zmk
 * @version   v0.1
 */
public interface IRoleManage {
    /**
     * 
     * 新增角色信息
     * @param roleName
     * @return	ResultBean
     * @exception	例外
     */
    public ResultBean addRole(String roleName,String user);
    /**
     * 
     * 编辑角色信息
     * @param roleId
     * @return	ResultBean
     * @exception	例外
     */
    public ResultBean editRole(String roleId,String roleName,String user);
    /**
     * 
     * 根据角色ID删除角色信息
     * @param roleId
     * @return
     * @return	ResultBean
     * @exception	例外
     */
    public ResultBean deleteRole(String roleId);
    /**
     * 
     * 根据角色信息关键字检索角色信息
     * @param roleKey
     * @return
     * @return	ResultBean
     * @exception	例外
     */
    public ResultBean queryRole(String userKey);
}
