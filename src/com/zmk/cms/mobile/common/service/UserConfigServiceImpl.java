package com.zmk.cms.mobile.common.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmk.cms.mobile.common.bean.UserConfig;
import com.zmk.cms.mobile.common.dao.UserConfigDao;
import com.zmk.cms.mobile.common.dao.UserDao;
import com.zmk.cms.mobile.common.service.UserConfigService;
import com.zmk.cms.mobile.common.util.DefaultUserConfig;

@Service
public class UserConfigServiceImpl implements UserConfigService{

	@Autowired
	private UserConfigDao userConfigDao;
	@Autowired
	private UserDao userDao;
 	//@Override
	public Map<String ,Object> getUserConfig(String username) {
		Map<String ,Object> uc=userConfigDao.findByUsername(username);
		if(uc==null){
			uc.put("id", userConfigDao.findMaxNo()+1+"");
 			uc.put("username", username);
 			uc.put("skin", DefaultUserConfig.DEFAULT_SKIN);
 			uc.put("monitorDepId", userDao.findByUsername(username).get("MONITOR_DEP_ID"));
 			uc.put("monitorSbTime", DefaultUserConfig.DEFAULT_MONITOR_SB_TIME);
 			uc.put("monitorSbLevel", DefaultUserConfig.DEFAULT_MONITOR_SB_LEVEL);
 			uc.put("monitorSbDo", DefaultUserConfig.DEFAULT_MONITOR_SB_DO);
 			uc.put("messageSbLevel", DefaultUserConfig.DEFAULT_MESSAGE_SB_LEVEL);
 			uc.put("messageBbLevel", DefaultUserConfig.DEFAULT_MESSAGE_BB_LEVEL);
  			uc.put("messageJcrk", DefaultUserConfig.DEFAULT_MESSAGE_JCRK);
 		}
		return uc;
	}
 
}
