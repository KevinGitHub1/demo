package com.zmk.cms.mobile.common.bean;

import com.zmk.cms.mobile.common.util.ResultStatus;
import com.zmk.cms.mobile.common.util.UserConfigChange;

public class MobileBean {
	private ResultStatus status;
	private Object result;
	private UserConfigChange configChange;
	private Object userConfig;
	
	public MobileBean() {
		
	}
	
	public MobileBean(ResultStatus status) {
		this.status = status;
	}
	
	
	public ResultStatus getStatus() {
		return status;
	}
	public void setStatus(ResultStatus status) {
		this.status = status;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	public UserConfigChange getConfigChange() {
		return configChange;
	}

	public void setConfigChange(UserConfigChange configChange) {
		this.configChange = configChange;
	}

	public Object getUserConfig() {
		return userConfig;
	}
	public void setUserConfig(Object userConfig) {
		this.userConfig = userConfig;
	}
	
	
}
