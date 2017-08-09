package com.zmk.cms.login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmk.cms.common.dao.ApacheDBUtils;
import com.zmk.cms.common.util.CommUtil;
import com.zmk.cms.common.util.MD5;
import com.zmk.cms.login.bean.User;
import com.zmk.cms.mobile.common.util.CommonUtils;

/*
 * 
 * ClassName: LoginServicesImpl 
 * @Description: 登录实现类
 * @author zhangmk
 * @date 2017-4-27
 */
@Service
public class LoginServicesImpl implements ILoginServices {

    @Autowired
    private ApacheDBUtils apacheDBUtils;
    private MD5 md = new MD5();
    
    public Map<String, Object> CheckUser(User user) {

        Map<String, Object> obj = new HashMap<String, Object>();
        // 判断用户名密码是否正确
        String loginName = user.getLoginName();
        String password = user.getPassword();
        String loginAdress = user.getLoginAdress();
        password = md.getMD5ofStr(password);//加密后的密码
        String sysdate = CommUtil.getCurrentTime();
        String sql = "SELECT ID,LOGIN_NAME,NAME,STATUS,LOGIN_ADRESS,LOGIN_TIME,MOBILE_NUMBER,EMAIL_ADRESS,CREATE_TIME" +
        		",BIRTHDAY,PROVINCE,CITY,COUNTY,ADRESS,MOTTO,HOBBY FROM T_SYS_USER t" +
        		" WHERE STATUS='1' AND LOGIN_NAME='"+loginName+"' AND PASSWORD='"+password+"'";
        try {
            obj = apacheDBUtils.findFirst(sql);
            if (obj != null) {
                //用户登录成功，更新用户最后登录时间地点信息
                String sql1 = "UPDATE T_SYS_USER SET LOGIN_TIME='"+sysdate+"',LOGIN_ADRESS='"+loginAdress+"' " +
                		"WHERE STATUS='1' AND LOGIN_NAME='"+loginName+"'";
                apacheDBUtils.update(sql1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public List<Map<String, Object>> findALLMenus() {
        String sql = "select * from T_SYS_MODULE t ";
        return apacheDBUtils.find(sql);
    }
}