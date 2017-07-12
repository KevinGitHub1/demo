package com.zmk.cms.mobile.common.dao;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.zmk.cms.common.dao.ApacheDBUtils;
import com.zmk.cms.mobile.common.bean.UserConfig;
import com.zmk.cms.mobile.common.dao.UserConfigDao;

@Repository
public class UserConfigDao{
	@Autowired
	private ApacheDBUtils apacheDBUtils;
	 
	public Map<String ,Object> findByUsername(String username) {
		String sql="select * from lmds_mobile_userconfig t   where t.username='"+username+"'";
		List<Map<String ,Object>> list = apacheDBUtils.find(sql);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
 	public int findMaxNo() {
		String sql="select * from lmds_mobile_userconfig order by cast(id as int) desc";
 		List<Map<String ,Object>> list = apacheDBUtils.find(sql);
		if(list.size()==0){
			return 0;
		}
 		return Integer.parseInt(list.get(0).get("ID").toString());
	}
}
