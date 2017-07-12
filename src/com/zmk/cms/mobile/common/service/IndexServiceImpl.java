package com.zmk.cms.mobile.common.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zmk.cms.mobile.common.bean.MobileBean;
import com.zmk.cms.mobile.common.bean.Token;
import com.zmk.cms.mobile.common.bean.User;
import com.zmk.cms.mobile.common.bean.UserConfig;
import com.zmk.cms.mobile.common.service.IndexService;
import com.zmk.cms.mobile.common.service.TokenService;
import com.zmk.cms.mobile.common.service.UserConfigService;
import com.zmk.cms.mobile.common.service.UserService;
import com.zmk.cms.mobile.common.util.CacheData;
import com.zmk.cms.mobile.common.util.ResultStatus;
import com.zmk.cms.mobile.common.util.UserConfigChange;

@Service
public class IndexServiceImpl implements IndexService{
 	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserConfigService userConfigService;
	//@Override
	public MobileBean login(String username, String password) {
		Map<String ,Object> user = userService.getUser(username, password);
		if(user==null){
			MobileBean bean=new MobileBean(ResultStatus.FAIL);
			bean.setResult("用户名密码错误");
			return bean;
		}else{
			Token token = tokenService.createTokenForUser(username);//为用户创建token
 			MobileBean bean=new MobileBean(ResultStatus.SUCCESS);
			bean.setResult(user);
			bean.setConfigChange(UserConfigChange.CHANGE);
  		//	MobileBean bean=new MobileBean(ResultStatus.SUCCESS);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("token", token.getTokenid());
			map.put("phone", user.get("PHONE"));
 			bean.setResult(map);
 			return bean;
		}
	}

	//@Override
	public MobileBean logout(String tokenId, String username) {
		MobileBean bean = new MobileBean();
		tokenService.removeToken(tokenId);
		bean.setStatus(ResultStatus.SUCCESS);
		bean.setResult("退出登录操作成功");
		return bean;
	}

	//@Override
	public MobileBean getUserConfig(String tokenId) {
		Map<String ,Object> user = userService.getUserByTokenId(tokenId);
		Token token = CacheData.TOKENDATA.get(tokenId);
		MobileBean bean=new MobileBean(ResultStatus.SUCCESS);
		if(user!=null){
			Map<String ,Object> uc=userConfigService.getUserConfig(user.get("USERNAME").toString());//获取用户设置
			bean.setResult(user);
			bean.setConfigChange(UserConfigChange.CHANGE);
			bean.setUserConfig(uc);
		}
		return bean;
	}
}
