package com.zmk.cms.mobile.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmk.cms.common.dao.ApacheDBUtils;
import com.zmk.cms.common.util.MD5;
import com.zmk.cms.mobile.common.bean.User;
import com.zmk.cms.mobile.common.dao.UserDao;
import com.zmk.cms.mobile.common.service.UserService;
import com.zmk.cms.mobile.common.util.CommonUtils;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private ApacheDBUtils apacheDBUtils;
	//@Override
	public Map<String ,Object> getUser(String username, String password) {
		MD5 md=new MD5();
		password=md.getEncodePass(password);
		String sql="select * from LMDS_J_USER t where t.username='"+username+"' and t.password='"+password+"'";
		List<Map<String ,Object>> list = apacheDBUtils.find(sql);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
 	}
	//@Override
	public Map<String ,Object> getUserByTokenId(String tokenid) {
		String username=CommonUtils.getUsernnameFromToken(tokenid);
		String sql="select * from LMDS_J_USER t  where t.username='"+username+"'";
		List<Map<String ,Object>> list = apacheDBUtils.find(sql);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}
}
