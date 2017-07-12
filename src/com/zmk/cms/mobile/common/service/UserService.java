package com.zmk.cms.mobile.common.service;

import java.util.Map;

import com.zmk.cms.mobile.common.bean.User;

public interface UserService {
 	public Map<String ,Object> getUser(String username,String password);
	public Map<String ,Object> getUserByTokenId(String tokenid);
}

