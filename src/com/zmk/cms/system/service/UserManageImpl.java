package com.zmk.cms.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmk.cms.common.bean.ResultBean;
import com.zmk.cms.common.dao.ApacheDBUtils;
import com.zmk.cms.common.util.CommUtil;
import com.zmk.cms.common.util.MD5;
import com.zmk.cms.common.util.ServiceUtil;
import com.zmk.cms.login.bean.User;
/**
 * 
 * <p>Title: UserManageImpl</p>
 * <p>Description: 类的功能描述</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 河南思维轨道交通技术研究院有限公司</p>
 * @author    作者
 * @version   版本
 */
@Service
public class UserManageImpl implements IUserManage{
    @Autowired
    private ApacheDBUtils apacheDBUtils;
    @Override
    public ResultBean addUser(User user) {
        ResultBean result = new ResultBean();
        String id = CommUtil.getUUID();
        
        String password = "123456";//默认密码：123456
        //密码加密
        MD5 md5 = new MD5();
        password = md5.getMD5ofStr(password);
        StringBuffer addUserSql = new StringBuffer();
        try {
            String loginNmae = user.getLoginName();
            String emailAdress = user.getEmailAdress();
            addUserSql.append("INSERT INTO T_SYS_USER (ID,LOGIN_NAME,PASSWORD,STATUS,CREATE_TIME,EMAIL_ADRESS) VALUES ('"+id+"','"+loginNmae+"','"
                    +password+"',1,SYSDATE,'"+emailAdress+"')");
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
}
