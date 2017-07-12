package com.zmk.cms.mobile.common.service;

import java.util.Map;

import com.zmk.cms.mobile.common.bean.UserConfig;


public interface UserConfigService {
	public Map<String ,Object> getUserConfig(String username);
}

