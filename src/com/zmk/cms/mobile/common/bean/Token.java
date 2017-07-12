package com.zmk.cms.mobile.common.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zmk.cms.mobile.common.util.UserConfigChange;

public class Token {
 
	private String tokenid;
	
	private String username;
	
	private Date createdate;
	
	private Date lastdate;
	 
 	private UserConfigChange config_change;
	 
	private String messageCode;
	
	public String getTokenid() {
		return tokenid;
	}
	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	public UserConfigChange getConfig_change() {
		return config_change;
	}
	public void setConfig_change(UserConfigChange configChange) {
		config_change = configChange;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	
 
}
