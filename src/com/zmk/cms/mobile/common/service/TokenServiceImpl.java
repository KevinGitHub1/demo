package com.zmk.cms.mobile.common.service;

import java.util.Date;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmk.cms.common.dao.ApacheDBUtils;
import com.zmk.cms.mobile.common.bean.Token;
import com.zmk.cms.mobile.common.service.TokenService;
import com.zmk.cms.mobile.common.util.CacheData;
import com.zmk.cms.mobile.common.util.CommonUtils;
import com.zmk.cms.mobile.common.util.UserConfigChange;

@Service
public class TokenServiceImpl implements TokenService {
 
	@Autowired
	private ApacheDBUtils apacheDBUtils;
	/**
	 * 为用户创建token，并且把token放入缓存中
	 */
	//@Override
	public Token createTokenForUser(String username) {
		Token token = new Token();
		String tokenid = CommonUtils.createToken(username);
		token.setTokenid(tokenid);
		token.setUsername(username);
		token.setCreatedate(new Date());
		token.setConfig_change(UserConfigChange.UNCHANGE);
		String sql=" insert into LMDS_MOBILE_TOKEN(TOKENID, USERNAME, CREATEDATE, CONFIG_CHANGE) values('"+tokenid+"','"+username+"',sysdate,'UNCHANGE')";
		apacheDBUtils.update(sql);
 		CacheData.TOKENDATA.put(tokenid, token);
		return token;
	}
	/**
	 * 在拦截器中调用此方法验证token是否合法
	 * 如果内存中有token信息验证通过
	 * 如果内存中没有token信息，从数据库中读取
	 * 如果数据库中有token信息，验证通过，并且把token信息存入内存
	 * 如果数据库中也没有token信息，验证失败
	 * 验证通过后在其他地方可直接从CacheData内存中取得token对象
	 */
	//@Override
	public boolean validateToken(String tokenId) {
		Token token = CacheData.TOKENDATA.get(tokenId);// 从内存中取token
		if (token != null) {
			return true;
		}
 		String sql=" select *  from LMDS_MOBILE_TOKEN t where t.tokenid='"+tokenId+"' ";
		List list=apacheDBUtils.find(sql);
		if (list!=null&&list.get(0) != null) {
			CacheData.TOKENDATA.put(token.getTokenid(), token);// 把token存入内存
			return true;
		}
		return false;
	}
	/**
	 * 移除token，包括数据库和内存中的token
	 */
	//@Override
	public void removeToken(String tokenId) {
		String sql=" delete  from LMDS_MOBILE_TOKEN t where t.tokenid='"+tokenId+"' ";
		apacheDBUtils.update(sql);
		CacheData.TOKENDATA.remove(tokenId);
	}
	 
}
