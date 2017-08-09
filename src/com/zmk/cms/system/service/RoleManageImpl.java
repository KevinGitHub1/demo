package com.zmk.cms.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmk.cms.common.bean.ResultBean;
import com.zmk.cms.common.dao.ApacheDBUtils;
import com.zmk.cms.common.util.CommUtil;
import com.zmk.cms.common.util.ServiceUtil;
/**
 * 
 * <p>Title: RoleManageImpl</p>
 * <p>Description: 角色管理各个 功能实现</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * @author    zmk
 * @version   版本
 */
@Service
public class RoleManageImpl implements IRoleManage{
    @Autowired
    private ApacheDBUtils apacheDBUtils;
    @Override
    public ResultBean addRole(String  roleName,String user) {
        ResultBean result = new ResultBean();
        String id = CommUtil.getUUID();
        String sysdate = CommUtil.getCurrentTime();
        StringBuffer addUserSql = new StringBuffer();
        try {
            addUserSql.append("INSERT INTO T_SYS_ROLE (ID,NAME,CREATE_TIME,CREATE_PERSION) " +
            		"VALUES ('"+id+"','"+roleName+"','"+sysdate+"','"+user+"')");
            int i = 0;
                i = apacheDBUtils.update(addUserSql.toString());
            if(i==1){
                result.setFlag("success");
                result.setContent(queryRole("").getContent());
            }else{
                result.setFlag("failure");
            }
        } catch (Exception e) {
            result.setFlag("failure");
            result.setErrorInfo("后台出现异常");
            result.setContent(e.toString());
        }
        return result;
    }

    public ResultBean deleteRole(String roleId) {
        ResultBean result = new ResultBean();
        int i = 0;
        String sql = "DELETE FROM T_SYS_ROLE t WHERE ID='"+roleId+"'";
        try {
            i = apacheDBUtils.update(sql);
            if (i == 1) {
                result.setFlag("success");
                result.setContent(queryRole("").getContent());
            } else {
                result.setFlag("failure");
            }
        } catch (Exception e) {
            result.setFlag("failure");
            result.setErrorInfo("删除数据时出错");
            result.setContent(e.toString());
        }
        return result;
    }

    public ResultBean queryRole(String roleKey) {
        ResultBean result = new ResultBean();
        ServiceUtil s = new ServiceUtil();
        s.getCurrentWeather();
        List<Map<String,Object>> list = null;
        String sql ="SELECT ID,NAME,to_char(CREATE_TIME,'yyyy-mm-dd') CREATE_TIME," +
        		"CREATE_PERSION,to_char(EDITE_TIME,'yyyy-mm-dd') EDITE_TIME,EDITE_PERSION FROM T_SYS_ROLE ";
        if(!("".equals(roleKey))&&null!=roleKey){
            sql = sql+"  WHERE NAME LIKE '%"+roleKey+"%'";
        }
        try {
            list = apacheDBUtils.find(sql);
            result.setFlag("success");
            result.setContent(list);
        } catch (Exception e) {
            result.setFlag("failure");
            result.setErrorInfo("查询数据时出错");
            result.setContent(e.toString());
        }
        return result;
    }


    @Override
    public ResultBean editRole(String roleId,String roleName,String user) {
        ResultBean result = new ResultBean();
        String sysdate = CommUtil.getCurrentTime();
        String sql = "UPDATE T_SYS_ROLE SET NAME='" + roleName
                + "',EDITE_PERSION='" + user
                + "',EDITE_TIME='" + sysdate
                + " WHERE ID='" + roleId + "'";
        int i = 0;
        try {
            i = apacheDBUtils.update(sql);
            if (i == 1) {
                result.setFlag("success");
                result.setContent(queryRole("").getContent());
            } else {
                result.setFlag("failure");
            }
        } catch (Exception e) {
            result.setFlag("failure");
            result.setErrorInfo("后台出现异常");
            result.setContent(e.toString());
        }
        return result;
    }

}
