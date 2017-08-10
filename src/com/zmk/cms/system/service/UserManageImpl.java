package com.zmk.cms.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmk.cms.common.bean.ResultBean;
import com.zmk.cms.common.dao.ApacheDBUtils;
import com.zmk.cms.common.util.CommUtil;
import com.zmk.cms.common.util.MD5;
import com.zmk.cms.common.util.ServiceUtil;
import com.zmk.cms.common.util.TreeObject;
import com.zmk.cms.login.bean.User;
/**
 * 
 * <p>Title: UserManageImpl</p>
 * <p>Description: 类的功能描述</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: </p>
 * @author    zmk
 * @version   v0.1
 */
@Service
public class UserManageImpl implements IUserManage{
    @Autowired
    private ApacheDBUtils apacheDBUtils;
    @Override
    public ResultBean addUser(User user) {
        ResultBean result = new ResultBean();
        String id = CommUtil.getUUID();
        String   sysdate = CommUtil.getCurrentTime();
        String password = "123456";//默认密码：123456
        //密码加密
        MD5 md5 = new MD5();
        password = md5.getMD5ofStr(password);
        StringBuffer addUserSql = new StringBuffer();
        try {
            String loginNmae = user.getLoginName();
            String emailAdress = user.getEmailAdress();
            addUserSql.append("INSERT INTO T_SYS_USER (ID,LOGIN_NAME,PASSWORD,STATUS,CREATE_TIME,EMAIL_ADRESS) VALUES ('"+id+"','"+loginNmae+"','"
                    +password+"','1','"+sysdate+"','"+emailAdress+"')");
            int i = 0;
                i = apacheDBUtils.update(addUserSql.toString());
            if(i==1){
                result.setFlag("success");
                result.setContent(id);
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

    public ResultBean deleteUser(String userId) {
        ResultBean result = new ResultBean();
        int i = 0;
        String sql = "DELETE FROM T_SYS_USER t WHERE ID='"+userId+"'";
        try {
            i = apacheDBUtils.update(sql);
            if (i == 1) {
                result.setFlag("success");
                result.setContent(userId);
            } else {
                result.setFlag("failure");
            }
        } catch (Exception e) {
            result.setFlag("failure");
            result.setErrorInfo("查询数据时出错");
            result.setContent(e.toString());
        }
        return result;
    }

    public ResultBean queryUser(String userKey) {
        ResultBean result = new ResultBean();
        ServiceUtil s = new ServiceUtil();
        s.getCurrentWeather();
        List<Map<String,Object>> list = null;
        String sql ="";
        if("".equals(userKey)||null==userKey){
            sql = "SELECT ID,LOGIN_NAME,NAME,STATUS,LOGIN_ADRESS,LOGIN_TIME," +
            "MOBILE_NUMBER,EMAIL_ADRESS,CREATE_TIME, to_char(birthday,'yyyy-mm-dd')BIRTHDAY,PROVINCE,CITY," +
            "COUNTY,ADRESS,MOTTO,HOBBY FROM T_SYS_USER t ";
        }else{
            sql = "SELECT * FROM (SELECT ID,LOGIN_NAME,NAME,STATUS,LOGIN_ADRESS,LOGIN_TIME," +
            "MOBILE_NUMBER,EMAIL_ADRESS,CREATE_TIME, to_char(birthday,'yyyy-mm-dd')BIRTHDAY,PROVINCE,CITY," +
            "COUNTY,ADRESS,MOTTO,HOBBY, LOGIN_NAME||NAME||MOBILE_NUMBER||EMAIL_ADRESS||" +
            "PROVINCE||CITY||COUNTY||ADRESS||MOTTO||HOBBY USERKEY FROM T_SYS_USER) t WHERE T.USERKEY LIKE '%"+userKey+"%'";
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
    public ResultBean getUserById(String userId) {
        ResultBean result = new ResultBean();
        String sql = "SELECT ID,LOGIN_NAME,NAME,STATUS,LOGIN_ADRESS,LOGIN_TIME,"
                + "MOBILE_NUMBER,EMAIL_ADRESS,CREATE_TIME, to_char(birthday,'yyyy-mm-dd')BIRTHDAY,PROVINCE,CITY,"
                + "COUNTY,ADRESS,MOTTO,HOBBY FROM T_SYS_USER t WHERE ID='"+userId+"'";
        try {
            List<Map<String, Object>> list = null;
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
    public ResultBean editUser(User user) {
        ResultBean result = new ResultBean();
        String sql = "UPDATE T_SYS_USER SET NAME='" + user.getUserName()
                + "',MOBILE_NUMBER='" + user.getMobileNumber()
                + "',EMAIL_ADRESS='" + user.getEmailAdress()
                + "',BIRTHDAY=TO_DATE('" + user.getBirthday()
                + "','yyyy-mm-dd')," + "PROVINCE='" + user.getProvince()
                + "',CITY='" + user.getCity() + "',COUNTY='" + user.getCounty()
                + "'," + "ADRESS='" + user.getAdress() + "',HOBBY='"
                + user.getHobby() + "',MOTTO='" + user.getMotto() + "'  "
                + "WHERE ID='" + user.getId() + "'";
        int i = 0;
        try {
            i = apacheDBUtils.update(sql);
            if (i == 1) {
                result.setFlag("success");
                result.setContent(user.getId());
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

    @Override
    public ResultBean changeUserStatus(String userId, String status) {
        ResultBean result = new ResultBean();
        String sql = "UPDATE T_SYS_USER SET STATUS='" + status
                + "'  WHERE ID='" + userId + "'";
        int i = 0;
        try {
            i = apacheDBUtils.update(sql);
            if (i == 1) {
                result.setFlag("success");
                result.setContent(userId);
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
//   public static void main(String args[]){
//       Integer i1=100;
//       Integer i2=100;
//       System.out.println(i1==i2);
//       System.out.println(i1.equals(i2));
//       Integer i3=1000;
//       Integer i4=1000;
//       
//       System.out.println(i3==i4);
//       System.out.println(i3.equals(i4));
//       int a = 1000;
//       int b = 1000;
//       System.out.println(a==b);
//   }

    @Override
    public ResultBean queryOrgaTree() {
        ResultBean result = new ResultBean();
        ArrayList<TreeObject> treeData = new ArrayList<TreeObject>();
        List<Map<String, Object>> treeRoot =  getTreeRoot();
        for(int i = 0;i<treeRoot.size();i++){
            TreeObject treeRootData = new TreeObject();
            String id = treeRoot.get(i).get("id").toString();
            String name = treeRoot.get(i).get("name").toString();
            String parentId = treeRoot.get(i).get("parentid").toString();
            String type = treeRoot.get(i).get("type").toString();
            treeRootData.setId(id);
            treeRootData.setName(name);
            treeRootData.setParentId(parentId);
            treeRootData.setType(type);
            TreeObject[] children = this.getTreeChild(id);
            treeRootData.setChildren(children);
            treeData.add(treeRootData);
        }
        result.setFlag("success");
        result.setContent(treeData);
        return result;
    }
    
    private List<Map<String, Object>> getTreeRoot() {
        String sql = "select * from T_sys_organization where parentid='0' ORDER BY deptno";
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = apacheDBUtils.find(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    private  TreeObject[] getTreeChild(String id) {
        String sql = "select * from T_sys_organization WHERE parentid ='"+id+"' ORDER by deptno";
        List<Map<String, Object>> children = apacheDBUtils.find(sql);
        int childNum = children.size();
        TreeObject[] childrenObject;
        if(childNum>0){
           childrenObject = new TreeObject[childNum];
        }else{
            childrenObject =null;//显示为叶子节点
        }
        
        for(int i=0;i<childNum;i++){
            TreeObject obj = new TreeObject();
           String childid = children.get(i).get("id").toString();
           String name = children.get(i).get("name").toString();
            String parentId = children.get(i).get("parentid").toString();
            String type = children.get(i).get("type").toString();
            obj.setId(childid);
            obj.setName(name);
            obj.setParentId(parentId);
            obj.setType(type);
            obj.setChildren(this.getTreeChild(childid));
            childrenObject[i]=obj;
        }
        return  childrenObject;
    }

    @Override
    public ResultBean addOrg(String orgname,String orgno,String loginName) {
        ResultBean result = new ResultBean();
        String id = CommUtil.getUUID();
        String   sysdate = CommUtil.getCurrentTime();
        String sql = "insert into t_sys_organization(id,name,type,parentid,deptno,create_persion,create_date)" +
        		" values('"+id+"','"+orgname+"','1','0','"+orgno+"','"+loginName+"','"+sysdate+"')";
        int i = 0;
        try {
                i = apacheDBUtils.update(sql);
            if(i==1){
                result.setFlag("success");
                result.setContent(id);
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
    @Override
    public ResultBean addDept(String deptname,String deptno,String parentid,String loginName) {
        ResultBean result = new ResultBean();
        String id = CommUtil.getUUID();
        String   sysdate = CommUtil.getCurrentTime();
        String sql = "insert into t_sys_organization(id,name,type,parentid,deptno,create_persion,create_date)" +
                " values('"+id+"','"+deptname+"','2','"+parentid+"','"+deptno+"','"+loginName+"','"+sysdate+"')";
        int i = 0;
        try {
                i = apacheDBUtils.update(sql);
            if(i==1){
                result.setFlag("success");
                result.setContent(id);
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
    @Override
    public ResultBean queryOrganization(String type,String key) {
        ResultBean result = new ResultBean();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String sql = "SELECT id,name,type,parentid,deptno FROM t_sys_organization ";
        String sqlWhere = "";
        if("1".equals(type)){
           sqlWhere = "ORDER BY deptno";
        }else if("2".equals(type)){
            sqlWhere = "where id='"+key+"' or parentid ='"+key+"' ORDER BY deptno";
        }else if("3".equals(type)){
            sqlWhere = "where name like '%"+key+"%' ORDER BY deptno";
        }
        sql = sql+sqlWhere;
        try {
            list = apacheDBUtils.find(sql);
            result.setFlag("success");
            result.setContent(list);
        } catch (Exception e) {
            result.setFlag("failure");
            result.setErrorInfo("后台出现异常");
            result.setContent(e.toString());
        }
       
        return result;
    }
    public ResultBean deleteOrg(String id) {
        ResultBean result = new ResultBean();
        int i = 0;
        //判断当前组织机构有无子节点，有的话进行提示禁止删除
        if(getDataById(id)){
            result.setFlag("failure");
            result.setErrorInfo("当前组织机构有子节点,禁止删除");
            result.setContent("");
        }else{
            String sql = "DELETE FROM T_SYS_organization t WHERE ID='"+id+"'";
            try {
                i = apacheDBUtils.update(sql);
                if (i == 1) {
                    result.setFlag("success");
                    result.setContent(id);
                } else {
                    result.setFlag("failure");
                }
            } catch (Exception e) {
                result.setFlag("failure");
                result.setErrorInfo("删除数据时出错");
                result.setContent(e.toString());
            } 
        }
        return result;
    }
    private boolean getDataById(String id){
        boolean result = false;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String sql = "SELECT id,name,type,parentid,deptno FROM t_sys_organization where parentid='"+id+"' ";
        list = apacheDBUtils.find(sql);
        if(list.size()>0){
            result = true;
        }
        return result;
    }

    @Override
    public ResultBean editOrg(String id, String orgname, String orgno,String loginName) {
        ResultBean result = new ResultBean();
        String   sysdate = CommUtil.getCurrentTime();
        String sql = "update t_sys_organization set name='"+orgname+"',deptno='"+orgno+"'," +
        		"edite_persion='"+loginName+"',edite_date='"+sysdate+"' where id='"+id+"' ";
        int i = 0;
        try {
            i = apacheDBUtils.update(sql);
            if (i == 1) {
                result.setFlag("success");
                result.setContent(id);
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
