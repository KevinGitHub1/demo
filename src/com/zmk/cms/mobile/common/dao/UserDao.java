package com.zmk.cms.mobile.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.zmk.cms.common.dao.ApacheDBUtils;
import com.zmk.cms.mobile.common.bean.User;
import com.zmk.cms.mobile.common.dao.UserDao;
@Repository
public class UserDao {
	@Autowired
	private ApacheDBUtils apacheDBUtils;
	 
 	public Map<String ,Object> findByUsernameAndPassword(String username, String password) {
		String sql=" select * from LMDS_J_USER t where t.username='"+username+"' and t.password='"+password+"'";
		List<Map<String ,Object>> list = apacheDBUtils.find(sql);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}
	public Map<String ,Object> findByUsername(String username) {
		String sql=" select * from LMDS_J_USER t where t.username='"+username+"'";
		List<Map<String ,Object>> list = apacheDBUtils.find(sql);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}
}
